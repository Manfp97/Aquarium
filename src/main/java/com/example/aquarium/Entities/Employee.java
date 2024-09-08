package com.example.aquarium.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;
}