package com.iordache.persistence.repositories.impl;

import com.iordache.domain.entity.Car;
import com.iordache.persistence.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class CarRepositoryImpl implements CarRepository {


    public final EntityManager entityManager;


    @Override
    public void createCar(Car car){
        entityManager.persist(car);
    }


    @Override
    public List<Car> findCarsByModel(String model){
        String jpql = "SELECT c FROM Car c WHERE c.model =: model";

        return entityManager.createQuery(jpql, Car.class)
                            .setParameter("model", model)
                            .getResultList();
    }


    @Override
    public int deleteCarById(int id){
        String jpql ="DELETE FROM Car c WHERE c.id =: id";

        return entityManager.createQuery(jpql)
                            .setParameter("id", id)
                            .executeUpdate();
    }


    @Override
    public Car updateEngineSpecsOfTheCar(Car car){
       return entityManager.merge(car);
    }


    @Override
    public Optional<Car> findCarById(int id){
        String jpql = "SELECT c FROM Car c WHERE c.id =: id";

        return entityManager.createQuery(jpql, Car.class)
                             .setParameter("id", id)
                             .getResultStream()
                             .findFirst();
    }
}
