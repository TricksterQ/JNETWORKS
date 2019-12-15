package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.pojo.RegisteredCarsCount;
import project.pojo.ResponseCar;
import project.pojo.Car;
import project.pojo.RegisteredCar;
import project.repository.RegisteredCarRepository;

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

    public RegisteredCarsCount getRegisteredCarsCount() {
        return new RegisteredCarsCount(registeredCarRepository.count());
    }

    public List<ResponseCar> getRegisteredCarsByNumberAndDate(String carNumber, String sDate, int page, int size) {
        LocalDate date = LocalDate.parse(sDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
        List<ResponseCar> resultDtos = new ArrayList<>();

        registeredCarRepository.findByCarNumberAndDate(carNumber, date, page, size).forEach(
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

    public List<ResponseCar> getRegisteredCars(int page, int size) {
        List<ResponseCar> resultDtos = new ArrayList<>();
        registeredCarRepository.findAll(page, size).forEach(
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

    public List<ResponseCar> getRegisteredCarsByNumber(String carNumber, int page, int size) {
        List<ResponseCar> resultDtos = new ArrayList<>();
        registeredCarRepository.findByCarNumber(carNumber, page, size).forEach(
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

    public List<ResponseCar> getRegisteredCarsByDate(String sDate, int page, int size) {
        LocalDate date = LocalDate.parse(sDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
        List<ResponseCar> resultDtos = new ArrayList<>();

        registeredCarRepository.findByDate(date, page, size).forEach(
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
