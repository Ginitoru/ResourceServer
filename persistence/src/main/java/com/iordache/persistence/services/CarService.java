package com.iordache.persistence.services;

import com.iordache.domain.entity.Car;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarService {
    @Transactional
    void saveCar(Car car);

    @Transactional
    Car findCarById(int id);

    @Transactional
    List<Car> findAllCars();
}
