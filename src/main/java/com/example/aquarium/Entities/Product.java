package com.example.aquarium.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.text.DecimalFormat;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private DecimalFormat price;

    @Column(name = "type")
    private String type;


}
