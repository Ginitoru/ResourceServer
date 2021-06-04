package com.iordache.persistence.services;

import com.iordache.domain.entity.Car;
import com.iordache.domain.entity.Engine;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarService {


    @Transactional
    void createCar(Car car);

    @Transactional(readOnly = true)
    List<Car> findCarsByModel(String model);

    @Transactional
    int deleteCar(int id);

    @Transactional
    Car updateEngineSpecsOfTheCar(Engine engine, int id);
}
