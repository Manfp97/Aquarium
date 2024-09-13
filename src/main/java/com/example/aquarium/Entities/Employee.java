package com.example.aquarium.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idemployee", nullable = false)
    private int idemployee;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idshop", foreignKey = @ForeignKey(name = "Fk_employee_product"))
    private Shop shop;

    @OneToMany(mappedBy = "idtank", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Tank> tanks;
}