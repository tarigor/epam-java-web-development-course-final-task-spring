package com.epam.hotelgrodnoinn.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @org.springframework.data.annotation.Transient
    private Long id;
    @Column(name = "client_order_id")
    private Integer clientOrderID;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
    @Column(name = "check_in_date")
    private Date dateFrom;
    @Column(name = "check_out_date")
    private Date datTo;
    @Column(name = "order_status")
    private String orderStatus;

    public Order() {
    }

    public Order(Integer clientOrderID, Request request, Room room, Date dateFrom, Date datTo, String orderStatus) {
        this.clientOrderID = clientOrderID;
        this.request = request;
        this.room = room;
        this.dateFrom = dateFrom;
        this.datTo = datTo;
        this.orderStatus = orderStatus;
    }

    public Integer getClientOrderID() {
        return clientOrderID;
    }

    public void setClientOrderID(Integer clientOrderID) {
        this.clientOrderID = clientOrderID;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDatTo() {
        return datTo;
    }

    public void setDatTo(Date datTo) {
        this.datTo = datTo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "clientOrderID=" + clientOrderID +
                ", dateFrom=" + dateFrom +
                ", datTo=" + datTo +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
