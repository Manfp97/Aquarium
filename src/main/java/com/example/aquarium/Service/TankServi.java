package com.example.aquarium.Service;

import com.example.aquarium.Entities.Tank;
import com.example.aquarium.Repositories.TankRepository;
import org.springframework.stereotype.Service;

@Service
public class TankServi extends AbstractBusinessService<Tank, Integer, TankRepository> {
    protected TankServi(TankRepository tankRepository) {super(tankRepository);}
}
