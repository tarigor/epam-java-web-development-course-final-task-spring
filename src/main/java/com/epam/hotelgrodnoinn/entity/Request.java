package com.epam.hotelgrodnoinn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "request")
public class Request implements Serializable {
    @Id
    @Column(name = "request_id")
    public Integer request_id;

    @Column(name = "persons_amount")
    public Integer personsAmount;

    @Column(name = "room_class")
    public String roomClass;

    @Column(name = "check_in_date")
    public Date checkInDate;

    @Column(name = "check_out_date")
    public Date checkOutDate;

    @Column(name = "request_status")
    public String requestStatus;

    public Request() {
    }

    public Request(Integer request_id, Integer personsAmount, String roomClass, Date checkInDate, Date checkOutDate, String requestStatus) {
        this.request_id = request_id;
        this.personsAmount = personsAmount;
        this.roomClass = roomClass;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.requestStatus = requestStatus;
    }

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }

    public Integer getPersonsAmount() {
        return personsAmount;
    }

    public void setPersonsAmount(Integer personsAmount) {
        this.personsAmount = personsAmount;
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

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    @Override
    public String toString() {
        return "Request{" +
                "request_id=" + request_id +
                ", personsAmount=" + personsAmount +
                ", roomClass='" + roomClass + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", requestStatus='" + requestStatus + '\'' +
                '}';
    }
}
