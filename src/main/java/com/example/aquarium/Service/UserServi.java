package com.example.aquarium.Service;

import com.example.aquarium.Entities.User;
import com.example.aquarium.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServi extends AbstractBusinessService<User, Integer, UserRepository> {

    protected UserServi(UserRepository userRepository)
    {super(userRepository);}

    public User findByUserName(String userName) {
        return getRepo().findUserByUsernameAndActiveTrue(userName)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public Optional<User> findByUsername(String username) {
        return getRepo().findUserByUsernameAndActiveTrue(username);
    }
}
