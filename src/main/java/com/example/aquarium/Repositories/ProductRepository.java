package com.example.aquarium.Repositories;

import com.example.aquarium.Entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByCategory(String category, Pageable pageable);

    Page<Product> findAll(Pageable pageable);
}