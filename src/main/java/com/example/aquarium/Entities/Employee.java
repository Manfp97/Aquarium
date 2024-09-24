package com.example.aquarium.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idemployee", nullable = false)
    private int idemployee;

    @Column(name = "nombre")
    private String name;

    @Column(name = "age")
    private int age;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idshop", foreignKey = @ForeignKey(name = "Fk_employee_shop"))
    private Shop idshop;
/*
    @OneToMany(mappedBy = "idtank", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Tank> tanks;
     */
}