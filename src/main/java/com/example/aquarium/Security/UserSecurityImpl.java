package com.example.aquarium.Security;

import com.example.aquarium.Entities.User;
import com.example.aquarium.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserSecurityImpl implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String getEncodedPassword(User user) {
        String plainPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(plainPassword);
        return encodedPassword;
    }

    @Override
    public org.springframework.security.core.userdetails.User loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername email : " + username);

        Optional<User> optionalUser = userRepository.findUserByUsernameAndActiveTrue(username);

        if (optionalUser.isEmpty()) {
            Optional<User> optionalUserAnonymous = userRepository.findUserByUsernameAndActiveTrue("anonymous");
            if (optionalUserAnonymous.isEmpty()) {
                throw new UsernameNotFoundException("User not found or not configured");
            }
            User userAnonymous = optionalUserAnonymous.get();
            System.out.println("Anonymous user : " + userAnonymous.getUsername());

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(userAnonymous.getRol().getNameRol()));

            return new org.springframework.security.core.userdetails.User(
                    userAnonymous.getUsername(),
                    userAnonymous.getPassword(),
                    grantedAuthorities);
        }

        User user = optionalUser.get();
        System.out.println("loadUserByUsername user : " + user.getUsername());

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRol().getNameRol()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities);
    }
}


