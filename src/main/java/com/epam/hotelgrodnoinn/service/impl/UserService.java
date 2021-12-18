package com.epam.hotelgrodnoinn.service.impl;

import com.epam.hotelgrodnoinn.entity.User;
import com.epam.hotelgrodnoinn.repa.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
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

    public Object checkUserExisting(User user) {

        try {
            User userFromDb = userRepository.getById((long) user.hashCode());
            if (passwordEncoder.matches(user.getPassword(), userFromDb.getPassword())) {
                log.info("there is such a user");
                return userFromDb;
            } else {
                return "login.wrong.password";
            }
        } catch (EntityNotFoundException ignored) {
            log.warn("no such a user");
            return "login.no.such.user";
        }
    }
}
