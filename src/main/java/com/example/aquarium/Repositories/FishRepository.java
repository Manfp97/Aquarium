package com.example.aquarium.Repositories;

import com.example.aquarium.Entities.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishRepository extends JpaRepository<Fish, Integer> {
}