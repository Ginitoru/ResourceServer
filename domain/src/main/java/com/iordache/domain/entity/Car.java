package com.iordache.domain.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iordache.domain.util.EngineType;
import com.iordache.domain.util.GearBox;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String brand;

    private String model;

    private int doors;

    @Enumerated(EnumType.STRING)
    private GearBox gearBox;


    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @JsonManagedReference  //ca sa rezolv problema de bidirectionalitate cu JSON -> aceasta adnotare face sa fie transformat obiectul in JSON
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private Engine engine;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return doors == car.doors && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && gearBox == car.gearBox && engineType == car.engineType && Objects.equals(engine, car.engine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, doors, gearBox, engineType, engine);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", doors=" + doors +
                ", gearBox=" + gearBox +
                ", engineType=" + engineType +
                ", engine=" + engine +
                '}';
    }
}
