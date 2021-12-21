package com.epam.hotelgrodnoinn.repa;

import com.epam.hotelgrodnoinn.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
