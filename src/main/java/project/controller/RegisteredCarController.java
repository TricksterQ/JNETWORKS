package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.pojo.Car;
import project.pojo.RegisteredCar;
import project.service.RegisteredCarService;

import java.util.logging.Logger;

@RestController
public class RegisteredCarController {

    @Autowired
    private RegisteredCarService registeredCarService;

    private Logger log = Logger.getLogger("RegisteredCarController");

    @PostMapping("/registeredCars")
    public RegisteredCar registerACar(@RequestBody Car newCar) {

        return registeredCarService.addCarToDatabase(newCar);

    }

}
