package com.epam.hotelgrodnoinn.repa;

import com.epam.hotelgrodnoinn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<User, Long> {
    User findByUserIDAndPassword(long userId, String password);

    @Query(value = "call charge_account(?1, ?2)", nativeQuery = true)
    Double changeBalance(long userID, Double chargeAmount);

}
