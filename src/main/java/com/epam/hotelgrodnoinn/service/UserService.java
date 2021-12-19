package com.epam.hotelgrodnoinn.service;

import com.epam.hotelgrodnoinn.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    @Transactional
    User getUserById(Long id);

    void doNewUserRegistration(User user);

    Object checkUserExisting(User user);
}
