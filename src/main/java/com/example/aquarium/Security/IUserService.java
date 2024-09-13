package com.example.aquarium.Security;

import com.example.aquarium.Entities.User;

public interface IUserService {

    public String getEncodedPassword(User user);
}
