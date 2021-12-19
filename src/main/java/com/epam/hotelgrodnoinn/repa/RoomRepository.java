package com.epam.hotelgrodnoinn.repa;

import com.epam.hotelgrodnoinn.entity.Order;
import com.epam.hotelgrodnoinn.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
}
