package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.pojo.Car;
import project.pojo.RegisteredCar;
import project.repository.RegisteredCarRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Logger;


@Service
public class RegisteredCarService {

    private Logger log = Logger.getLogger("RegisteredCarService");

    @Autowired
    private RegisteredCarRepository rcRepository;

    public RegisteredCar addCarToDatabase(Car newCar) {

        RegisteredCar registeredCar = new RegisteredCar();
        registeredCar.setId(0);
        registeredCar.setCarNumber(newCar.getCarNumber());
        Date date = new Date();
        registeredCar.setTimestamp(new Timestamp(date.getTime()));

        rcRepository.save(registeredCar);

        log.info("New registered car: " + newCar);

        return registeredCar;
    }

}
