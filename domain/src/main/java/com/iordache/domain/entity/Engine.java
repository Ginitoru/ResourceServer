package com.iordache.domain.entity;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "engine")
    private Car car;

}
