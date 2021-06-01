package com.iordache.web.controller;

import com.iordache.domain.entity.Car;
import com.iordache.persistence.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auto")
public class CarController {

    private final CarService carService;

    @PostMapping("/car")
    public void saveCar(@RequestBody Car car){
        carService.saveCar(car);
    }
}
