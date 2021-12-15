package com.epam.hotelgrodnoinn.types;

/**
 * Provides an ENUMs of the available room's classes.
 */
public enum RoomClass {
    SINGLE("single"),
    DOUBLE("double"),
    SUITE("suite"),
    DELUXE("deluxe");
    String description;

    RoomClass(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
