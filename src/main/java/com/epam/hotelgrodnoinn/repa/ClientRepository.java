package com.epam.hotelgrodnoinn.repa;

import com.epam.hotelgrodnoinn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<User, Long> {
}
