package com.epam.hotelgrodnoinn.entity;

import com.epam.hotelgrodnoinn.types.OrderStatus;

import java.sql.Date;

public class OrderInvoiceForm {
    private int roomID;
    private Date checkInDate;
    private Date checkOutDate;
    private OrderStatus orderStatus;

    public OrderInvoiceForm(int roomID, Date checkInDate, Date checkOutDate, OrderStatus orderStatus) {
        this.roomID = roomID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.orderStatus = orderStatus;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "roomID=" + roomID +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
