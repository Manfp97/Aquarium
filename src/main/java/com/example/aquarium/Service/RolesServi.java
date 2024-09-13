package com.example.aquarium.Service;

import com.example.aquarium.Entities.Roles;
import com.example.aquarium.Repositories.RolesRepository;
import org.springframework.stereotype.Service;

@Service
public class RolesServi extends AbstractBusinessService<Roles, Integer, RolesRepository> {

    protected RolesServi(RolesRepository rolesRepository) {super(rolesRepository);}
}
