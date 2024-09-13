package com.example.aquarium.Service;

import com.example.aquarium.Entities.DeliveryNote;
import com.example.aquarium.Repositories.DeliveryNoteRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliveryNoteServi extends AbstractBusinessService<DeliveryNote, Integer, DeliveryNoteRepository> {
    protected DeliveryNoteServi(DeliveryNoteRepository deliveryNoteRepository) {super(deliveryNoteRepository);}
}
