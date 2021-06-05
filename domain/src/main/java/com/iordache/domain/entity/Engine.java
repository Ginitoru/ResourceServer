package com.iordache.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "engines")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;


    private String engineName;
    private int cylinders;
    private double horsePower;
    private double torque;
    private double cylindricalCapacity;

    @JsonBackReference  //ca sa rezolv problema de bidirectionalitate cu JSON -> aceasta adnotare face sa nu mai fie transformat obiectul in JSON ce ar duce la exceptia de stackOverflow
    @OneToOne(mappedBy = "engine")
    private Car car;

    @Override
    public String toString() {
        return "Engine{" +
                "id=" + id +
                ", engineName='" + engineName + '\'' +
                ", cylinders=" + cylinders +
                ", horsePower=" + horsePower +
                ", torque=" + torque +
                ", cylindricalCapacity=" + cylindricalCapacity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return cylinders == engine.cylinders && Double.compare(engine.horsePower, horsePower) == 0 && Double.compare(engine.torque, torque) == 0 && Double.compare(engine.cylindricalCapacity, cylindricalCapacity) == 0 && Objects.equals(engineName, engine.engineName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engineName, cylinders, horsePower, torque, cylindricalCapacity);
    }
}
