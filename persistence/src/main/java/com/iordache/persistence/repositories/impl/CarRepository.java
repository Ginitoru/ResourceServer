package com.iordache.persistence.repositories.impl;

import com.iordache.domain.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {


    @Query("SELECT c FROM Car c")
    List<Car> findAllCars();

    void deleteCarById(int id);




}
