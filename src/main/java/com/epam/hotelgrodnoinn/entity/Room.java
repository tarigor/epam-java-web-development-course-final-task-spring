package com.epam.hotelgrodnoinn.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_class_id", nullable = false)
    private RoomClass roomClass;
    @Column(name = "room_cost")
    private Double roomCost;
    @Column(name = "persons_in_room")
    private Integer persons;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private Set<Order> orders;

    public Room() {
    }

    public Room(RoomClass roomClass, Double roomCost, Integer persons, Set<Order> orders) {
        this.roomClass = roomClass;
        this.roomCost = roomCost;
        this.persons = persons;
        this.orders = orders;
    }

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }

    public Double getRoomCost() {
        return roomCost;
    }

    public void setRoomCost(Double roomCost) {
        this.roomCost = roomCost;
    }

    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        this.persons = persons;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomClass=" + roomClass +
                ", roomCost=" + roomCost +
                ", persons=" + persons +
                ", orders=" + orders +
                '}';
    }
}
