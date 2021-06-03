package com.iordache.domain.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iordache.domain.util.EngineType;
import com.iordache.domain.util.GearBox;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Engine engine;








}
