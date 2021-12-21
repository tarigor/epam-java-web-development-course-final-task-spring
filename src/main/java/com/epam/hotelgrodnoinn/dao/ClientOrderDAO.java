package com.epam.hotelgrodnoinn.dao;

import com.epam.hotelgrodnoinn.entity.ClientOrderView;
import com.epam.hotelgrodnoinn.entity.ClientRequestView;
import com.epam.hotelgrodnoinn.types.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientOrderDAO {

    private static final String SQL_GET_ALL_ORDERS = "" +
            "SELECT client_order_id,request_id,first_name,last_name,email,room_id,room_class,check_in_date,check_out_date,order_status " +
            "FROM orders " +
            "join client_order c on orders.client_order_id = c.client_order_room_id " +
            "join user u on c.client_id = u.id " +
            "join client_order co on orders.client_order_id = co.client_order_room_id " +
            "join room r on orders.room_id = r.id " +
            "join room_class rc on r.room_class_id = rc.id";
    private static final String SQL_GET_CLIENT_ORDER = "" +
            "SELECT client_order_id, request_id, room_id, room_class, check_in_date, check_out_date, order_status " +
            "FROM orders o " +
            "join client_order co on o.client_order_id = co.client_order_room_id " +
            "join room r on o.room_id = r.id " +
            "join room_class rc on r.room_class_id = rc.id " +
            "where co.client_id = :userID";
    private static final String SQL_CLIENT_ORDER_BY_ID_AND_EMAIL = "" +
            "select request_id, client_r_id, first_name, last_name, email, persons_amount, room_class, check_in_date, check_out_date, request_status " +
            "FROM request " +
            "JOIN client_request cr on request.request_id = cr.client_request_id " +
            "JOIN user u on cr.client_r_id = u.id " +
            "where request_id = :requestID and email= :email";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<ClientOrderView> getAllOrders() {
        return jdbcTemplate.query(SQL_GET_ALL_ORDERS, (resultSet, rowNum) -> new ClientOrderView(
                resultSet.getLong(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getInt(6),
                resultSet.getString(7),
                resultSet.getDate(8),
                resultSet.getDate(9),
                OrderStatus.valueOf(resultSet.getString(10))));
    }

    public List<ClientOrderView> getClientOrder(long userID) {
        return jdbcTemplate.query(SQL_GET_CLIENT_ORDER, new MapSqlParameterSource("userID", userID), (resultSet, rowNum) -> new ClientOrderView(
                resultSet.getLong(1),
                resultSet.getInt(2),
                resultSet.getInt(3),
                resultSet.getString(4),
                resultSet.getDate(5),
                resultSet.getDate(6),
                OrderStatus.valueOf(resultSet.getString(7))));
    }

    public ClientRequestView getClientRequestByIdAndEmail(int requestID, String email) {
        return jdbcTemplate.queryForObject(
                SQL_CLIENT_ORDER_BY_ID_AND_EMAIL,
                new MapSqlParameterSource()
                        .addValue("requestID", requestID)
                        .addValue("email", email),
                (resultSet, rowNum) -> new ClientRequestView(
                        resultSet.getInt(1),
                        resultSet.getLong(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getDate(8),
                        resultSet.getDate(9),
                        OrderStatus.valueOf(resultSet.getString(10))
                ));
    }
}
