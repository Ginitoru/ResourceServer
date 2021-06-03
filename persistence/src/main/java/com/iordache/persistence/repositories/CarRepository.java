package com.iordache.persistence.repositories;

import com.iordache.domain.entity.Car;
import com.iordache.domain.entity.Engine;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarRepository {
    void createCar(Car car);

    List<Car> findCarsByModel(String model);


    int deleteCar(int id);

    int updateEngineSpecs(Engine eungine, int id);
}
