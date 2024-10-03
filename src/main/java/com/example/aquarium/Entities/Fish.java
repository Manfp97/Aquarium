package com.example.aquarium.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fish")
@Getter
@Setter
@NoArgsConstructor
public class Fish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "descripcion")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idtank", foreignKey = @ForeignKey(name = "idfish"))
    private Tank tank;


    public Fish(String name, String scientificname, String habitat, String sex, int numberindivs, String description, Tank tank) {
        this.name = name;
        this.scientificname = scientificname;
        this.habitat = habitat;
        this.sex = sex;
        this.numberindivs = numberindivs;
        this.description = description;
    }

}