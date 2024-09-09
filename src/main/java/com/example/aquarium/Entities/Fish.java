package com.example.aquarium.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "fish")
public class Fish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "scientificname")
    private String scientificname;

    @Column(name = "habitat")
    private String habitat;

    @Column(name = "sex")
    private String sex;

    @Column(name = "numberindivs")
    private int numberindivs;

}