package com.example.aquarium.Repositories;

import com.example.aquarium.Entities.DeliveryNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryNoteRepository extends JpaRepository<DeliveryNote, Integer> {
}