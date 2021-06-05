package com.iordache.persistence.repositories;

import com.iordache.domain.entity.Car;
import com.iordache.domain.entity.Engine;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CarRepository {
    void createCar(Car car);

    List<Car> findCarsByModel(String model);

    int deleteCarById(int id);

    Car updateEngineSpecsOfTheCar(Car car);

    Optional<Car> findCarById(int id);
}
