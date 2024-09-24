package com.example.aquarium.Entities;

import jakarta.persistence.*;
import lombok.Data;
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

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idtank", foreignKey = @ForeignKey(name = "idfish"))
    private Tank tank;

}