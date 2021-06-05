package com.iordache.persistence.services.impl;

import com.iordache.domain.entity.Car;
import com.iordache.domain.entity.Engine;
import com.iordache.exception.error.CarAlreadyExists;
import com.iordache.exception.error.CarNotFoundException;
import com.iordache.persistence.repositories.impl.CarRepositoryImpl;
import com.iordache.persistence.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;


@AllArgsConstructor
@Service
public class CarServiceImpl implements CarService {


    private final CarRepositoryImpl carRepository;


    @Override
    @Transactional  //method 2
    @PreAuthorize("hasRole('ADMIN')")
    public void createCar(Car car){

       carRepository.findCarsByModel(car.getModel())
                                        .stream()
                                        .filter(theCar -> theCar.equals(car))
                                        .findFirst()
                                        .ifPresentOrElse(theCar -> throwsException(),
                                                        () ->carRepository.createCar(car));
    }

    //method 1
    private void throwsException(){
        throw new CarAlreadyExists("The car already exists in the database");
    }


    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('{ADMIN, USER}')")
    public List<Car> findCarsByModel(String model){
        return carRepository.findCarsByModel(model);
    }


    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public int deleteCarById(int carId){
        return carRepository.deleteCarById(carId);
    }


    @Override
    @Transactional
    @Lock(LockModeType.OPTIMISTIC) //method 2
    @PreAuthorize("hasRole('ADMIN')")
    public Car updateEngineSpecsOfTheCar(Engine engine, int carId){

        return carRepository.findCarById(carId)
                            .map(car -> updateEngine(car, engine))
                            .orElseThrow(() -> new CarNotFoundException("Car not found by id"));

    }

    //method 1
    private Car updateEngine(Car car, Engine engine){

        int engineId = car.getEngine().getId();
        engine.setId(engineId);
        car.setEngine(engine);

        return carRepository.updateEngineSpecsOfTheCar(car);
    }
}
