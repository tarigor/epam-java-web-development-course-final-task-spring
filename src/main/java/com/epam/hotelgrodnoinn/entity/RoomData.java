package com.epam.hotelgrodnoinn.entity;

import com.epam.hotelgrodnoinn.types.RoomClassType;

public class RoomData {
    double cost;
    private int persons;
    private RoomClassType roomClass;

    public RoomData(int persons, RoomClassType roomClass, double cost) {
        this.persons = persons;
        this.roomClass = roomClass;
        this.cost = cost;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public RoomClassType getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomClassType roomClass) {
        this.roomClass = roomClass;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "RoomData{" +
                "persons=" + persons +
                ", roomClass=" + roomClass +
                ", cost=" + cost +
                '}';
    }
}
