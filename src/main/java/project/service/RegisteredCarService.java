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

    @Autowired
    private RegisteredCarRepository rcRepository;

    public void addCarToDatabase(Car newCar) {

        RegisteredCar registeredCar = new RegisteredCar();
        registeredCar.setId(0);
        registeredCar.setCarNumber(newCar.getCarNumber());
        Date date = new Date();
        registeredCar.setDate(new Timestamp(date.getTime()));

        rcRepository.save(registeredCar);

    }

}
