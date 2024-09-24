package com.example.aquarium.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idroles", nullable = false)
    private Integer idroles;

    @Column(name = "name_rol", nullable = false, length = 50)
    private String nameRol;

    @OneToMany(mappedBy = "rol")
    @ToString.Exclude
    private Set<User> users = new HashSet<>();
}
