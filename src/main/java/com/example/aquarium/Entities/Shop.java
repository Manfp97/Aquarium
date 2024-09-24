package com.example.aquarium.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idshop", nullable = false)
    private Integer idshop;

    @OneToMany(mappedBy = "idproduct", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Product> products;

    @OneToMany(mappedBy = "idemployee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Employee> employees;

    }

