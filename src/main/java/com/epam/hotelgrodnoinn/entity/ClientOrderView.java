package com.epam.hotelgrodnoinn.entity;

import com.epam.hotelgrodnoinn.types.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ClientOrderView {
    private long orderID;
    private int requestID;
    private boolean canBeCanceled;
    private String firstName;
    private String lastName;
    private String email;
    private int roomID;
    private String roomClass;
    private Date checkInDate;
    private Date checkOutDate;
    private OrderStatus orderStatus;
    private boolean paymentRequired;

    public ClientOrderView() {
    }

    public ClientOrderView(long orderID, int requestID, String firstName, String lastName, String email, int roomID, String roomClass, Date checkInDate, Date checkOutDate, OrderStatus orderStatus) {
        this.orderID = orderID;
        this.requestID = requestID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roomID = roomID;
        this.roomClass = roomClass;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.orderStatus = orderStatus;
        if (orderStatus.equals(OrderStatus.WAITING_FOR_APPROVAL)) {
            setCanBeCanceled(true);
        }
    }

    public ClientOrderView(long orderID, int requestID, int roomID, String roomClass, Date checkInDate, Date checkOutDate, OrderStatus orderStatus) {
        this.orderID = orderID;
        this.requestID = requestID;
        this.roomID = roomID;
        this.roomClass = roomClass;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.orderStatus = orderStatus;
        if (orderStatus.equals(OrderStatus.WAITING_FOR_APPROVAL)) {
            setCanBeCanceled(true);
        }
        if (orderStatus.equals(OrderStatus.APPROVED_WAITING_FOR_PAYMENT)) {
            setPaymentRequired(true);
        }
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public boolean isCanBeCanceled() {
        return canBeCanceled;
    }

    public void setCanBeCanceled(boolean canBeCanceled) {
        this.canBeCanceled = canBeCanceled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
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

    public boolean isPaymentRequired() {
        return paymentRequired;
    }

    public void setPaymentRequired(boolean paymentRequired) {
        this.paymentRequired = paymentRequired;
    }

    @Override
    public String toString() {
        return "ClientOrderView{" +
                "orderID=" + orderID +
                ", requestId=" + requestID +
                ", canBeCanceled=" + canBeCanceled +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", roomID=" + roomID +
                ", roomClass='" + roomClass + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", orderStatus=" + orderStatus +
                ", paymentRequired=" + paymentRequired +
                '}';
    }
}
