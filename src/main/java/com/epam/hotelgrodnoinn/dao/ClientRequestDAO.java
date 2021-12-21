package com.epam.hotelgrodnoinn.dao;

import com.epam.hotelgrodnoinn.entity.ClientRequestView;
import com.epam.hotelgrodnoinn.types.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRequestDAO {

    private static final String SQL_GET_ALL_REQUESTS = "" +
            "select request_id,client_r_id,first_name,last_name,email,persons_amount,room_class,check_in_date,check_out_date,request_status " +
            "FROM request " +
            "JOIN client_request cr on request.request_id = cr.client_request_id " +
            "JOIN user u on cr.client_r_id = u.id ORDER BY request_id";
    private static final String SQL_GET_CLIENT_REQUEST = "" +
            "select request_id,persons_amount,room_class,check_in_date,check_out_date,request_status " +
            "from request " +
            "join client_request cr on request.request_id = cr.client_request_id where client_r_id = :userID";
    private static final String CHANGE_REQUEST_STATUS = "UPDATE request t SET t.request_status = :requestProcessed WHERE t.request_id = :requestID";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<ClientRequestView> getClientRequests() {
        return jdbcTemplate.query(SQL_GET_ALL_REQUESTS, (resultSet, rowNum) -> new ClientRequestView(
                resultSet.getInt(1),
                resultSet.getLong(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getInt(6),
                resultSet.getString(7),
                resultSet.getDate(8),
                resultSet.getDate(9),
                OrderStatus.valueOf(resultSet.getString(10))));
    }

    public List<ClientRequestView> getClientRequest(Long userID) {
        return jdbcTemplate.query(SQL_GET_CLIENT_REQUEST, new MapSqlParameterSource("userID", userID), (resultSet, rowNum) -> new ClientRequestView(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getDate(4),
                resultSet.getDate(5),
                OrderStatus.valueOf(resultSet.getString(6))));
    }

    public void changeStatusOfRequest(int requestID, String requestProcessed) {
        jdbcTemplate.update(CHANGE_REQUEST_STATUS, new MapSqlParameterSource()
                .addValue("requestID", requestID)
                .addValue("requestProcessed", requestProcessed));
    }
}
