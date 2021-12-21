package com.epam.hotelgrodnoinn.dao;

import com.epam.hotelgrodnoinn.entity.RoomData;
import com.epam.hotelgrodnoinn.entity.RoomView;
import com.epam.hotelgrodnoinn.types.RoomClassType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class RoomDAO {

    private static final String SQL_GET_FREE_ROOMS = "call get_free_rooms(:dateFrom,:dateTo)";
    private static final String SQL_GET_ROOMS_DATA = "" +
            "select distinct persons_in_room, room_class, room_cost from room " +
            "join room_class rc on room.room_class_id = rc.id " +
            "order by persons_in_room";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<RoomView> getFreeRooms(Date dateFromSQL, Date dateToSQL) {
        return jdbcTemplate.query(SQL_GET_FREE_ROOMS, new MapSqlParameterSource()
                        .addValue("dateFrom", dateFromSQL)
                        .addValue("dateTo", dateToSQL),
                (resultSet, rowNum) -> new RoomView(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
    }

    public List<RoomData> getRoomsData() {
        return jdbcTemplate.query(SQL_GET_ROOMS_DATA, (resultSet, rowNum) -> new RoomData(
                resultSet.getInt(1),
                RoomClassType.valueOf(resultSet.getString(2)),
                resultSet.getDouble(3)
        ));
    }
}
