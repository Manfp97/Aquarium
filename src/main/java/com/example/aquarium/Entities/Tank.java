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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtank", nullable = false)
    private Integer idtank;

    @Column(name = "size")
    private Double size;

    @Column(name = "categoria")
    private String category;

    public Tank(Integer idtank, Double size,
                String category) {
        this.size = size;
        this.idtank = idtank;
        this.category = category;
    }
}
