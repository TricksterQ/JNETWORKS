package project.service;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.pojo.RegisteredCarsCount;
import project.pojo.ResponseCar;
import project.pojo.Car;
import project.pojo.RegisteredCar;
import project.repository.RegCarRepository;
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

    @Autowired
    private RegCarRepository regCarRepository;

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

    public List<ResponseCar> getRegisteredCarsByNumberAndDate(String carNumber, String sDate) {
        LocalDate date = LocalDate.parse(sDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
        List<ResponseCar> resultDtos = new ArrayList<>();

        regCarRepository.findByCarNumberAndDate(carNumber, date).forEach(
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
        regCarRepository.findAll().forEach(
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
        regCarRepository.findByCarNumber(carNumber).forEach(
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

        regCarRepository.findByDate(date).forEach(
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
