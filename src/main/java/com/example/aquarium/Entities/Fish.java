package com.example.aquarium.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "fish")
public class Fish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idfish", nullable = false)
    private Integer idfish;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idtank", foreignKey = @ForeignKey(name = "Fk_fish_tank"))
    private Tank tank;

}