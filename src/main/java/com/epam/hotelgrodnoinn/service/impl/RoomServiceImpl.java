package com.epam.hotelgrodnoinn.service.impl;

import com.epam.hotelgrodnoinn.dao.RoomDAO;
import com.epam.hotelgrodnoinn.entity.RoomData;
import com.epam.hotelgrodnoinn.entity.RoomView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class RoomServiceImpl {

    @Autowired
    private RoomDAO roomDAO;

    private static Date convertStringToSqlDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateSQL = null;
        try {
            java.util.Date dateUtil = formatter.parse(date);
            dateSQL = new Date(dateUtil.getTime());
        } catch (ParseException e) {
            e.getStackTrace();
        }
        return dateSQL;
    }

    public List<RoomView> getFreeRooms(String dateFrom, String dateTo) {
        Date dateFromSQL = convertStringToSqlDate(dateFrom);
        Date dateToSQL = convertStringToSqlDate(dateTo);
        return roomDAO.getFreeRooms(dateFromSQL, dateToSQL);
    }

    public List<RoomData> getRoomsData() {
        return roomDAO.getRoomsData();
    }
}
