package com.iordache.persistence.services.impl;

import com.iordache.domain.entity.Car;
import com.iordache.domain.entity.Engine;
import com.iordache.persistence.repositories.impl.CarRepositoryImpl;
import com.iordache.persistence.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@AllArgsConstructor
@Service
public class CarServiceImpl implements CarService {


    private final CarRepositoryImpl carRepository;


    @Override
    @Transactional
    public void createCar(Car car){
        carRepository.createCar(car);
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
    public int updateEngineSpecs(Engine engine, int id){
        return carRepository.updateEngineSpecs(engine, id);
    }









}
