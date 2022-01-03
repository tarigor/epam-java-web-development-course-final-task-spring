package com.epam.hotelgrodnoinn.service;

import com.epam.hotelgrodnoinn.entity.RoomData;
import com.epam.hotelgrodnoinn.entity.RoomView;

import java.util.List;

public interface IRoomService {
    List<RoomView> getFreeRooms(String dateFrom, String dateTo);

    List<RoomData> getRoomsData();
}
