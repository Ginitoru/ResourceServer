package com.iordache.web.controller;

import com.iordache.domain.entity.Car;
import com.iordache.domain.entity.Engine;;
import com.iordache.persistence.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/auto")
public class CarController {

    private final CarService carService;

    @PostMapping("/car")
    public void createCar(@RequestBody Car car){
        carService.createCar(car);
    }


    @GetMapping("/cars/{model}")
    public List<Car> findCarsByModel(@PathVariable String model){
       return carService.findCarsByModel(model);
    }


    @DeleteMapping("/car/{carId}")
    public int deleteCar(@PathVariable int carId){
        return carService.deleteCarById(carId);
    }


    @PutMapping("/car/{carId}")
    public Car updateEngineSpecsOfTheCar(@RequestBody Engine engine, @PathVariable int carId){
        return carService.updateEngineSpecsOfTheCar(engine, carId);
    }


}
