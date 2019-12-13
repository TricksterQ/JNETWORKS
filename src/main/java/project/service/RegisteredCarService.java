package project.service;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import project.pojo.RegisteredCarsCount;
import project.pojo.ResponseCar;
import project.pojo.Car;
import project.pojo.RegisteredCar;
import project.repository.RegisteredCarRepository;

import java.time.OffsetDateTime;
import java.util.logging.Logger;


@Service
public class RegisteredCarService {

    private Logger log = Logger.getLogger("RegisteredCarService");

    @Autowired
    private RegisteredCarRepository registeredCarRepository;

    public ResponseCar addCarToDatabase(Car newCar) {
        RegisteredCar registeredCar = new RegisteredCar(
                newCar.getCarNumber(),
                OffsetDateTime.now()
        );
        registeredCarRepository.save(registeredCar);
        log.info("New registered car: " + registeredCar);

        return new ResponseCar(registeredCar.getCarNumber(), registeredCar.getTimestamp());
    }

    public RegisteredCarsCount getRegisteredCarsCount() {
        return new RegisteredCarsCount(registeredCarRepository.count());
    }


    public Page<RegisteredCar> getRegisteredCars(Predicate predicate, int page, int size) {
        return registeredCarRepository.findAll(predicate, PageRequest.of(page, size));
    }

}
