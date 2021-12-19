package com.epam.hotelgrodnoinn.entity;

import java.util.Arrays;

public class RequestData {

    private String persons;
    private String roomClass;
    private String[] dateFilter;

    public RequestData(String persons, String roomClass, String[] dateFilter) {
        this.persons = persons;
        this.roomClass = roomClass;
        this.dateFilter = dateFilter;
    }

    public String getPersons() {
        return persons;
    }

    public void setPersons(String persons) {
        this.persons = persons;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public String[] getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(String[] dateFilter) {
        this.dateFilter = dateFilter;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "persons='" + persons + '\'' +
                ", roomClass='" + roomClass + '\'' +
                ", dateFilter=" + Arrays.toString(dateFilter) +
                '}';
    }
}
