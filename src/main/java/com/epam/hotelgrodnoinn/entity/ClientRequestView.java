package com.epam.hotelgrodnoinn.entity;

import com.epam.hotelgrodnoinn.types.OrderStatus;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class ClientRequestView {

    public static final String PATTERN = "yyyy-MM-dd";
    public static final String REGEX = ",";
    private Integer requestID;
    private Integer persons;
    private String roomClass;
    private Date dateFrom;
    private Date dateTo;
    private OrderStatus requestStatus;
    private boolean processed;

    public ClientRequestView() {
    }

    public ClientRequestView(String string) {
        String[] array = string.split(REGEX);
        this.requestID = Integer.valueOf(array[0]);
        this.persons = Integer.valueOf(array[1]);
        this.roomClass = array[2];
        this.dateFrom = convertStringToSqlDate(array[3]);
        this.dateTo = convertStringToSqlDate(array[4]);
        this.requestStatus = OrderStatus.valueOf(array[5]);
        if (requestStatus.equals(OrderStatus.WAITING_FOR_APPROVAL)) {
            setProcessed(true);
        }
    }

    protected static Date convertStringToSqlDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat(PATTERN);
        Date dateSQL = null;
        try {
            java.util.Date dateUtil = formatter.parse(date);
            dateSQL = new Date(dateUtil.getTime());
        } catch (ParseException e) {
            e.getStackTrace();
        }
        return dateSQL;
    }

    public ClientRequestView setResult(String string) {
        return new ClientRequestView(string);
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public Integer getRequestID() {
        return requestID;
    }

    public void setRequestID(Integer requestID) {
        this.requestID = requestID;
    }

    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        this.persons = persons;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public OrderStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(OrderStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    @Override
    public String toString() {
        return "ClientRequestQuery{" +
                "requestId=" + requestID +
                ", persons=" + persons +
                ", roomClass='" + roomClass + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", requestStatus='" + requestStatus + '\'' +
                '}';
    }
}
