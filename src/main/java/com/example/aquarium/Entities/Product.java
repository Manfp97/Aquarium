package com.example.aquarium.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;

@Entity
@Getter
@Setter
@Table(name = "product")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idproduct", nullable = false)
    private Integer idproduct;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private DecimalFormat price;

    @Column(name = "category")
    private String category;

    @Column(name = "type")
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idproductdelivered", foreignKey = @ForeignKey(name = "Fk_product_delivery_note"))
    private Product delivered;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "iduser", foreignKey = @ForeignKey(name = "Fk_user_product"))
    private User productuser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idshop", foreignKey = @ForeignKey(name = "Fk_shop_product"))
    private Shop productshop;

}
