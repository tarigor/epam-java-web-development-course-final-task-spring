package com.epam.hotelgrodnoinn.entity;

import com.epam.hotelgrodnoinn.types.OrderStatus;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ClientOrderView {
    public static final String PATTERN = "yyyy-MM-dd";
    public static final String REGEX = ",";
    private int orderID;
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

    public ClientOrderView(String string) {
        String[] array = string.split(REGEX);
        this.orderID = Integer.parseInt(array[0]);
        this.requestID = Integer.parseInt(array[1]);
        this.roomID = Integer.parseInt(array[2]);
        this.roomClass = array[3];
        this.checkInDate = convertStringToSqlDate(array[4]);
        this.checkOutDate = convertStringToSqlDate(array[5]);
        this.orderStatus = OrderStatus.valueOf(array[6]);
        if (orderStatus.equals(OrderStatus.WAITING_FOR_APPROVAL)) {
            setCanBeCanceled(true);
        }
        if (orderStatus.equals(OrderStatus.APPROVED_WAITING_FOR_PAYMENT)) {
            setPaymentRequired(true);
        }
    }

    protected static java.sql.Date convertStringToSqlDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat(PATTERN);
        java.sql.Date dateSQL = null;
        try {
            java.util.Date dateUtil = formatter.parse(date);
            dateSQL = new java.sql.Date(dateUtil.getTime());
        } catch (ParseException e) {
            e.getStackTrace();
        }
        return dateSQL;
    }

    public ClientOrderView setResult(String string) {
        return new ClientOrderView(string);
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
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
