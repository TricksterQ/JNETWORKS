package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.pojo.Car;
import project.pojo.RegisteredCarsCount;
import project.pojo.ResponseCar;
import project.service.RegisteredCarService;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;


@RestController
public class RegisteredCarController {

    @Autowired
    private RegisteredCarService registeredCarService;

    private Logger log = Logger.getLogger("RegisteredCarController");

    @PostMapping("/registeredCars")
    public ResponseCar registerACar(@Valid @RequestBody Car newCar) {
        return registeredCarService.addCarToDatabase(newCar);
    }

    @GetMapping("/registeredCars/count")
    public RegisteredCarsCount registeredCarsCount() {
        return registeredCarService.getRegisteredCarsCount();
    }

    @GetMapping("/RegisteredCars")
    public List<ResponseCar> getRegisteredCars(
            @RequestParam(name = "carNumber", required = false) String carNumber,
            @RequestParam(name = "date", required = false) String sDate,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        if (carNumber != null && sDate != null)
            return registeredCarService.getRegisteredCarsByNumberAndDate(carNumber, sDate, page, size);

        if (carNumber != null)
            return registeredCarService.getRegisteredCarsByNumber(carNumber, page, size);

        if (sDate != null)
            return registeredCarService.getRegisteredCarsByDate(sDate, page, size);

        return registeredCarService.getRegisteredCars(page, size);
    }
}
