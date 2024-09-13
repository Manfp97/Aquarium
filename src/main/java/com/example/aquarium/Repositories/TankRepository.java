package com.example.aquarium.Repositories;

import com.example.aquarium.Entities.Tank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TankRepository extends JpaRepository<Tank, Integer> {
    Page<Tank> findByCategory(String category, Pageable pageable);

    Page<Tank> findAll(Pageable pageable);
}