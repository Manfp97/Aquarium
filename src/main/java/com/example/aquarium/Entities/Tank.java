package com.example.aquarium.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tank")
public class Tank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idtank", nullable = false)
    private Integer idtank;

    @Column(name = "size")
    private DecimalFormat size;

    @Column(name = "category")
    private String category;

    public Tank(Integer idtank, DecimalFormat size,
                String category) {
        this.size = size;
        this.idtank = idtank;
        this.category = category;
    }
}
