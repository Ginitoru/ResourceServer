package com.iordache.persistence.services.impl;

import com.iordache.domain.entity.Car;
import com.iordache.persistence.repositories.impl.CarRepository;
import com.iordache.persistence.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@AllArgsConstructor
@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;


    @Override
    @Transactional
    public void saveCar(Car car){
        carRepository.save(car);
    }


    @Override
    @Transactional
    public Car findCarById(int id){
        return carRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Car not found by id"));
    }


    @Override
    @Transactional
    public List<Car> findAllCars(){
        return carRepository.findAllCars();
    }





}
