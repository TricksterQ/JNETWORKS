package project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.pojo.RegisteredCar;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class RegCarRepository {

    private Logger log = Logger.getLogger("RegisteredCarRepository");

    @Autowired
    private EntityManager entityManager;

    public List<RegisteredCar> findByCarNumberAndDate(String carNumber, LocalDate date) {
        log.info("Search a car by number: " + carNumber + "and date" + date);
        return entityManager.createQuery("from registered_car where car_number like :carNumber and formatdatetime(timestamp, 'yyyy-MM-dd') like :date", RegisteredCar.class)
                .setParameter("carNumber", "%" + carNumber + "%")
                .setParameter("date", "%" + date + "%")
                .getResultList();
    }

    public List<RegisteredCar> findByCarNumber(String carNumber) {
        return entityManager.createQuery("from registered_car where car_number like :carNumber", RegisteredCar.class)

                .setParameter("carNumber", "%" + carNumber + "%")
                .getResultList();
    }


    public List<RegisteredCar> findByDate(LocalDate date) {
        return entityManager.createQuery("from registered_car where formatdatetime(timestamp, 'yyyy-MM-dd') like :date", RegisteredCar.class)
                .setParameter("date", "%" + date + "%")
                .getResultList();
    }

    public List<RegisteredCar> findAll() {
        return entityManager.createQuery("from registered_car", RegisteredCar.class)
                .getResultList();
    }


}
