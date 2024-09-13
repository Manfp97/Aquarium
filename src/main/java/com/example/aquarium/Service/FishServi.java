package com.example.aquarium.Service;

import com.example.aquarium.Entities.Fish;
import com.example.aquarium.Repositories.FishRepository;
import org.springframework.stereotype.Service;

@Service
public class FishServi extends AbstractBusinessService<Fish, Integer, FishRepository> {
    protected FishServi(FishRepository fishRepository) {super(fishRepository);}
}
