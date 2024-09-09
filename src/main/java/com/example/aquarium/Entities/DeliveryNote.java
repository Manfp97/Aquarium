package com.example.aquarium.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.text.DecimalFormat;
import java.util.Date;

public class DeliveryNote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column (name = "price")
    private int price;

    @Column(name = "startingdate")
    private Date startingdate;

    @Column(name = "finishingdate")
    private Date finishingdate;
}
