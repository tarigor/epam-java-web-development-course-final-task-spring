package com.epam.hotelgrodnoinn.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "room_class")
public class RoomClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "room_class")
    private String roomClass;

    @OneToMany(mappedBy = "roomClass", cascade = CascadeType.ALL)
    private Set<Room> rooms;

    public RoomClass() {
    }

    public RoomClass(Integer id, String roomClass) {
        this.id = id;
        this.roomClass = roomClass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }
}
