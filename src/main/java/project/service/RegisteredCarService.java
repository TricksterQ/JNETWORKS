package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.pojo.RegisteredCarsCounter;
import project.pojo.ResponseCar;
import project.pojo.Car;
import project.pojo.RegisteredCar;
import project.repository.RegisteredCarRepository;
import project.repository.RegisteredCarCounterRepository;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Service
public class RegisteredCarService {

    private Logger log = Logger.getLogger("RegisteredCarService");

    @Autowired
    private RegisteredCarCounterRepository registeredCarCounterRepository;

    @Autowired
    private RegisteredCarRepository registeredCarRepository;

    @Transactional
    public ResponseCar addCarToDatabase(Car newCar) {
        RegisteredCar registeredCar = new RegisteredCar(
                newCar.getCarNumber(),
                OffsetDateTime.now()
        );
        registeredCarRepository.save(registeredCar);
        log.info("New registered car: " + registeredCar);

        return new ResponseCar(registeredCar.getCarNumber(), registeredCar.getTimestamp());
    }

    public RegisteredCarsCounter getRegisteredCarsCount() {
        return new RegisteredCarsCounter(registeredCarCounterRepository.count());
    }

    public List<ResponseCar> getRegisteredCarsByNumberAndDate(String carNumber, String sDate) {
        LocalDate date = LocalDate.parse(sDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
        List<ResponseCar> resultDtos = new ArrayList<>();

        registeredCarRepository.findByCarNumberAndDate(carNumber, date).forEach(
                RegisteredCar -> {
                    ResponseCar car = new ResponseCar(
                            RegisteredCar.getCarNumber(),
                            RegisteredCar.getTimestamp()
                    );
                    resultDtos.add(car);
                }
        );
        return resultDtos;
    }

    public List<ResponseCar> getRegisteredCars() {
        List<ResponseCar> resultDtos = new ArrayList<>();
        registeredCarRepository.findAll().forEach(
                RegisteredCar -> {
                    ResponseCar car = new ResponseCar(
                            RegisteredCar.getCarNumber(),
                            RegisteredCar.getTimestamp()
                    );
                    resultDtos.add(car);
                }
        );
        return resultDtos;
    }

    public List<ResponseCar> getRegisteredCarsByNumber(String carNumber) {
        List<ResponseCar> resultDtos = new ArrayList<>();
        registeredCarRepository.findByCarNumber(carNumber).forEach(
                RegisteredCar -> {
                    ResponseCar car = new ResponseCar(
                            RegisteredCar.getCarNumber(),
                            RegisteredCar.getTimestamp()
                    );
                    resultDtos.add(car);
                }
        );
        return resultDtos;
    }

    public List<ResponseCar> getRegisteredCarsByDate(String sDate) {
        LocalDate date = LocalDate.parse(sDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
        List<ResponseCar> resultDtos = new ArrayList<>();

        registeredCarRepository.findByDate(date).forEach(
                RegisteredCar -> {
                    ResponseCar car = new ResponseCar(
                            RegisteredCar.getCarNumber(),
                            RegisteredCar.getTimestamp()
                    );
                    resultDtos.add(car);
                }
        );
        return resultDtos;
    }
}
