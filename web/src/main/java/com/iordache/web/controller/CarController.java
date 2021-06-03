package com.iordache.web.controller;

import com.iordache.domain.entity.Car;
import com.iordache.domain.entity.Engine;
import com.iordache.domain.util.EngineType;
import com.iordache.domain.util.GearBox;
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
    public void createCar(){
        Car car = new Car();
        car.setBrand("ferrari");
        car.setModel("f50");
        car.setDoors(2);
//        car.setGearBox(GearBox.AUTOMATIC);
//        car.setEngineType(EngineType.ELECTRIC);
        Engine engine = new Engine();
        engine.setEngineName("1.68");
        engine.setCylinders(5);
        engine.setCylindricalCapacity(168.5);
        engine.setTorque(25.6);
        engine.setHorsePower(1230);
        car.setEngine(engine);


        carService.createCar(car);
    }


    @GetMapping("/cars/{model}")
    public List<Car> findCarsByModel(@PathVariable String model){
       return carService.findCarsByModel(model);
    }
}
