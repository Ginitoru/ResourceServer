package com.iordache.persistence.services.impl;

import com.iordache.domain.entity.Car;
import com.iordache.domain.entity.Engine;
import com.iordache.exception.error.CarAlreadyExists;
import com.iordache.exception.error.CarNotFoundException;
import com.iordache.persistence.repositories.impl.CarRepositoryImpl;
import com.iordache.persistence.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
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
    public void createCar(Car car){

       carRepository.findCarsByModel(car.getModel())
                                        .stream()
                                        .filter(c -> c.equals(car))
                                        .findFirst()
                                        .ifPresentOrElse(x -> throwException(),
                                                        () ->carRepository.createCar(car));
    }

    //method 1
    private void throwException(){
        throw new CarAlreadyExists("The car already exists in the database");
    }


    @Override
    @Transactional(readOnly = true)
    public List<Car> findCarsByModel(String model){
        return carRepository.findCarsByModel(model);
    }

    @Override
    @Transactional
    public int deleteCar(int id){
        return carRepository.deleteCar(id);
    }


    @Override
    @Transactional
    @Lock(LockModeType.OPTIMISTIC) //method 2
    public Car updateEngineSpecsOfTheCar(Engine engine, int carId){

        return carRepository.findCarById(carId)
                            .map(c -> update(c, engine))
                            .orElseThrow(() -> new CarNotFoundException("Car not found by id"));

    }
        
    //method 1
    private Car update(Car car, Engine engine){

        int engineId = car.getEngine().getId();
        engine.setId(engineId);
        car.setEngine(engine);

        return carRepository.updateEngineSpecsOfTheCar(car);
    }








}
