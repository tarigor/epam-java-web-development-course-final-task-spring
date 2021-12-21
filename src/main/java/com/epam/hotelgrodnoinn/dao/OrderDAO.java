package com.epam.hotelgrodnoinn.dao;

import com.epam.hotelgrodnoinn.entity.OrderInvoiceForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDAO {

    private static final String INSERT_ORDER_INTO_TWO_TABLES = "call insert_new_order(?,?,?,?,?,?,?)";
    private static final String INSERT_ORDER_INTO_SINGLE_TABLE =
            "insert into `orders` (client_order_id, request_id, room_id, check_in_date, check_out_date, order_status) VALUES (:client_order_id,:request_id,:room_id,:check_in_date,:check_out_date,:order_status)";
    private static final String CHANGE_ORDER_STATUS = "UPDATE orders SET order_status = :order_status WHERE client_order_id = :client_order_id AND request_id = :request_id AND room_id =:room_id";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertOrderDataIntoTwoTable(Long clientID, Integer requestID, OrderInvoiceForm orderInvoiceForm) {
        List prmtrsList = new ArrayList();
        prmtrsList.add(new SqlParameter(Types.BIGINT));
        prmtrsList.add(new SqlParameter(Types.INTEGER));
        prmtrsList.add(new SqlParameter(Types.INTEGER));
        prmtrsList.add(new SqlParameter(Types.DATE));
        prmtrsList.add(new SqlParameter(Types.DATE));
        prmtrsList.add(new SqlParameter(Types.CHAR));
        prmtrsList.add(new SqlOutParameter("result", Types.INTEGER));

        Map<String, Object> resultData = jdbcTemplate.call(connection -> {
            CallableStatement callableStatement = connection.prepareCall(INSERT_ORDER_INTO_TWO_TABLES);
            callableStatement.setLong(1, clientID);
            callableStatement.setInt(2, requestID);
            callableStatement.setInt(3, orderInvoiceForm.getRoomID());
            callableStatement.setDate(4, orderInvoiceForm.getCheckInDate());
            callableStatement.setDate(5, orderInvoiceForm.getCheckOutDate());
            callableStatement.setString(6, orderInvoiceForm.getOrderStatus().name());
            callableStatement.registerOutParameter(7, Types.INTEGER);
            return callableStatement;
        }, prmtrsList);
        return (int) resultData.get("result");
    }

    public void insertOrderDataIntoSingleTable(int orderIdAssigned, int requestID, OrderInvoiceForm orderInvoiceForm) {
        namedParameterJdbcTemplate.update(INSERT_ORDER_INTO_SINGLE_TABLE, new MapSqlParameterSource()
                .addValue("client_order_id", orderIdAssigned)
                .addValue("request_id", requestID)
                .addValue("room_id", orderInvoiceForm.getRoomID())
                .addValue("check_in_date", orderInvoiceForm.getCheckInDate())
                .addValue("check_out_date", orderInvoiceForm.getCheckOutDate())
                .addValue("order_status", orderInvoiceForm.getOrderStatus().name()));
    }

    public void changeStatusOfOrder(String paidAndBooked, int orderID, int requestID, int roomID) {
        namedParameterJdbcTemplate.update(CHANGE_ORDER_STATUS, new MapSqlParameterSource()
                .addValue("order_status", paidAndBooked)
                .addValue("client_order_id", orderID)
                .addValue("request_id", requestID)
                .addValue("room_id", roomID));
    }
}
