package com.example.aquarium.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "deliverynote")
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

    @OneToMany(mappedBy = "idproduct", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Product> products;
}
