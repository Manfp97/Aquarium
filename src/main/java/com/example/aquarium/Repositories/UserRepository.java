package com.example.aquarium.Repositories;

import com.example.aquarium.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsernameAndActiveTrue(String username);

    Page<User> findAll(Pageable pageable);

}