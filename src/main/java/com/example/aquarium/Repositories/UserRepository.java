package com.example.aquarium.Repositories;

import com.example.aquarium.Entities.Roles;
import com.example.aquarium.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsernameAndActiveTrue(String username);

    Page<User> findAll(Pageable pageable);

    Page<User> findByRol(Roles rol, Pageable pageable);

    Optional<User> findByResetToken(String resetToken);

    @Query("SELECT u FROM User u JOIN u.userDetails d WHERE d.mail = :mail AND u.active = true")
    Optional<User> findActiveUserByMail(@Param("mail") String mail);
}