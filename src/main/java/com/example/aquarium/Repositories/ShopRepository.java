package com.example.aquarium.Repositories;

import com.example.aquarium.Entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
}