package com.epam.hotelgrodnoinn.service.impl;

import com.epam.hotelgrodnoinn.entity.User;
import com.epam.hotelgrodnoinn.repa.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class UserService {

    @Autowired
    ClientRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }


    public void doNewUserRegistration(User user) {
        System.out.println("result->"+passwordEncoder.matches("Qwer1234!","$2a$10$q6Q7lp.by6iPVxJcsjJBJOVsE1ub2wNLbUBj89oC.Kx95RNZkDUSa"));
        System.out.println("password->"+passwordEncoder.encode(user.getPassword()));
        User userToDb = new User(
                user.hashCode(),
                user.getFirstName(),
                user.getLastName(),
                user.getUserType(),
                user.getEmail(),
                passwordEncoder.encode(user.getPassword()),
                0.0);
        userRepository.save(userToDb);
    }
}
