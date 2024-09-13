package com.example.aquarium.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "userdetails")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "user")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduserdetails", nullable = false)
    private Integer iduserdetails;

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @Column(name = "surname", nullable = false, length = 500)
    private String surname;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "passport", length = 10)
    private String passport;

    @Column(name = "mail", length = 100)
    private String mail;

    @OneToOne(mappedBy = "userDetails")
    private User user;
}
