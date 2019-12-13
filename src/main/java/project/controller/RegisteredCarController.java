package project.controller;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import project.pojo.Car;
import project.pojo.RegisteredCarsCount;
import project.pojo.ResponseCar;
import project.pojo.RegisteredCar;
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
    public List<ResponseCar> getRegisteredCarsList(
            @RequestParam(name = "carNumber", required = false) String carNumber,
            @RequestParam(name = "date", required = false) String sDate
    ) {
        if (carNumber != null && sDate != null)
            return  registeredCarService.getRegisteredCarsByNumberAndDate(carNumber, sDate);

        if (carNumber != null)
            return registeredCarService.getRegisteredCarsByNumber(carNumber);

        if (sDate != null)
            return registeredCarService.getRegisteredCarsByDate(sDate);

        return registeredCarService.getRegisteredCars();
    }
}
