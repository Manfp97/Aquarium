package com.example.aquarium.Entities;

import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"userDetails", "rol"})
@Table(name = "usuarios")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "active")
    private boolean active = true;

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "token_expiration")
    private LocalDateTime tokenExpiration;

    @OneToMany(mappedBy = "idproduct", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Roles rol;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_detail_id", referencedColumnName = "iduserdetails")
    private UserDetails userDetails;

}
