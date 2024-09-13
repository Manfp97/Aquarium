package com.example.aquarium.Service;

import com.example.aquarium.Entities.UserDetails;
import com.example.aquarium.Repositories.UserDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServi extends AbstractBusinessService<UserDetails, Integer, UserDetailsRepository> {
    protected UserDetailsServi(UserDetailsRepository userDetailsRepository) {super(userDetailsRepository);}
}
