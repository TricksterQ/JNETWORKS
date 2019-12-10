package project.controller;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import project.pojo.Car;
import project.pojo.RegisteredCar;
import project.service.RegisteredCarService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.logging.Logger;


@RestController
public class RegisteredCarController {

    @Autowired
    private RegisteredCarService registeredCarService;

    private Logger log = Logger.getLogger("RegisteredCarController");

    @PostMapping("/registeredCars")
    public RegisteredCar registerACar(@Valid @RequestBody Car newCar) {
        return registeredCarService.addCarToDatabase(newCar);
    }

    @GetMapping("/registeredCars/count")
    public Long registeredCarsCount() {
        return registeredCarService.getRegisteredCarsCount();

    }

    @GetMapping("/registeredCars")
    public Page<RegisteredCar> getFilteredRegisteredCars(
            @QuerydslPredicate(root = RegisteredCar.class) Predicate predicate,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return registeredCarService.getRegisteredCars(predicate, page, size);
    }

}
