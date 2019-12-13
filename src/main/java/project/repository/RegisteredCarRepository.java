package project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.pojo.RegisteredCar;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class RegisteredCarRepository {

    private Logger log = Logger.getLogger("RegisteredCarCounterRepository");

    @Autowired
    private EntityManager entityManager;

    public void save(RegisteredCar car) {
        entityManager.persist(car);
    }

    public List<RegisteredCar> findByCarNumberAndDate(String carNumber, LocalDate date, int page, int size) {
        log.info("Search a car by number: " + carNumber + "and date" + date);
        return entityManager.createQuery("from registered_car where car_number like :carNumber and formatdatetime(timestamp, 'yyyy-MM-dd') like :date", RegisteredCar.class)
                .setParameter("carNumber", "%" + carNumber + "%")
                .setParameter("date", "%" + date + "%")
                .setFirstResult((page * size) - size)
                .setMaxResults(size)
                .getResultList();
    }

    public List<RegisteredCar> findByCarNumber(String carNumber, int page, int size) {
        return entityManager.createQuery("from registered_car where car_number like :carNumber", RegisteredCar.class)
                .setParameter("carNumber", "%" + carNumber + "%")
                .setFirstResult((page * size) - size)
                .setMaxResults(size)
                .getResultList();
    }


    public List<RegisteredCar> findByDate(LocalDate date, int page, int size) {
        return entityManager.createQuery("from registered_car where formatdatetime(timestamp, 'yyyy-MM-dd') like :date", RegisteredCar.class)
                .setParameter("date", "%" + date + "%")
                .setFirstResult((page * size) - size)
                .setMaxResults(size)
                .getResultList();
    }

    public List<RegisteredCar> findAll(int page, int size) {
        return entityManager.createQuery("from registered_car", RegisteredCar.class)
                .setFirstResult((page * size) - size)
                .setMaxResults(size)
                .getResultList();
    }

}
