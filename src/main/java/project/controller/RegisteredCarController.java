package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.pojo.Car;
import project.service.RegisteredCarService;

import java.util.logging.Logger;

@RestController
public class RegisteredCarController {

    @Autowired
    private RegisteredCarService registeredCarService;

    private Logger log = Logger.getLogger("RegisteredCarController");

    @PostMapping("/registeredCars")
    public void registerACar(@RequestBody Car newCar) {
        registeredCarService.addCarToDatabase(newCar);
        log.info("New registered registeredCar: " + newCar);

    }

}
