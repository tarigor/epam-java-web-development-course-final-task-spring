package com.epam.hotelgrodnoinn.entity;

import org.springframework.stereotype.Component;

@Component
public class RoomView {
    private String roomType;
    private Integer roomID;

    public RoomView() {
    }

    public RoomView(Integer roomID, String roomType) {
        this.roomType = roomType;
        this.roomID = roomID;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomType='" + roomType + '\'' +
                ", roomID='" + roomID + '\'' +
                '}';
    }
}
