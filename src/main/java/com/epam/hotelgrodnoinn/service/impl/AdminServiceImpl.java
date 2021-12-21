package com.epam.hotelgrodnoinn.service.impl;

import com.epam.hotelgrodnoinn.dao.ClientOrderDAO;
import com.epam.hotelgrodnoinn.dao.ClientRequestDAO;
import com.epam.hotelgrodnoinn.dao.OrderDAO;
import com.epam.hotelgrodnoinn.entity.ClientOrderView;
import com.epam.hotelgrodnoinn.entity.ClientRequestView;
import com.epam.hotelgrodnoinn.entity.OrderInvoiceForm;
import com.epam.hotelgrodnoinn.types.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Component
public class AdminServiceImpl {

    @Autowired
    private ClientOrderDAO clientOrderDAO;
    @Autowired
    private ClientRequestDAO clientRequestDAO;
    @Autowired
    private OrderDAO orderDAO;

    protected static Date convertStringToSqlDate(String date) {
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

    public List<ClientOrderView> getAllOrders() {
        return clientOrderDAO.getAllOrders();
    }

    public List<ClientRequestView> getAllRequestsForAdmin() {
        return clientRequestDAO.getClientRequests();
    }

    public ClientRequestView getClientRequest(String requestID, String email) {
        return clientOrderDAO.getClientRequestByIdAndEmail(Integer.parseInt(requestID), email);
    }

    @Transactional
    public void insertNewOrder(String clientID, String requestID, String[] singleRoomsSelected, String[] doubleRoomsSelected, String[] suiteRoomsSelected, String[] deluxeRoomsSelected, String dateFrom, String dateTo) {
        ArrayList<OrderInvoiceForm> orders = new ArrayList<>();
        addOrder(singleRoomsSelected, dateFrom, dateTo, orders);
        addOrder(doubleRoomsSelected, dateFrom, dateTo, orders);
        addOrder(suiteRoomsSelected, dateFrom, dateTo, orders);
        addOrder(deluxeRoomsSelected, dateFrom, dateTo, orders);
        if (orders.size() > 1) {
            int orderIdAssigned = orderDAO.insertOrderDataIntoTwoTable(Long.parseLong(clientID), Integer.parseInt(requestID), orders.get(0));
            for (int i = 1; i < orders.size(); i++) {
                orderDAO.insertOrderDataIntoSingleTable(orderIdAssigned, Integer.parseInt(requestID), orders.get(i));
            }
            clientRequestDAO.changeStatusOfRequest(Integer.parseInt(requestID), OrderStatus.REQUEST_PROCESSED.name());
        } else if (orders.size() == 1) {
            int count = orderDAO.insertOrderDataIntoTwoTable(Long.parseLong(clientID), Integer.parseInt(requestID), orders.get(0));
            clientRequestDAO.changeStatusOfRequest(Integer.parseInt(requestID), OrderStatus.REQUEST_PROCESSED.name());
        }
    }

    private void addOrder(String[] roomsSelected, String dateFrom, String dateTo, ArrayList<OrderInvoiceForm> clientOrderRooms) {
        Date dateFromSQL = convertStringToSqlDate(dateFrom);
        Date dateToSQL = convertStringToSqlDate(dateTo);
        if (roomsSelected != null && roomsSelected.length != 0) {
            for (String s : roomsSelected) {
                clientOrderRooms.add(new OrderInvoiceForm(Integer.parseInt(s), dateFromSQL, dateToSQL, OrderStatus.APPROVED_WAITING_FOR_PAYMENT));
            }
        }
    }

    public void rejectRequest(int requestID) {
        clientRequestDAO.changeStatusOfRequest(requestID,OrderStatus.REJECTED.name());
    }
}
