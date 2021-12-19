package com.epam.hotelgrodnoinn.service.impl;

import com.epam.hotelgrodnoinn.entity.ClientOrderView;
import com.epam.hotelgrodnoinn.entity.ClientRequestView;
import com.epam.hotelgrodnoinn.entity.User;
import com.epam.hotelgrodnoinn.repa.ClientRepository;
import com.epam.hotelgrodnoinn.repa.OrderRepository;
import com.epam.hotelgrodnoinn.repa.RequestRepository;
import com.epam.hotelgrodnoinn.types.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Component
public class ClientServiceImpl {

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private ClientRequestView clientRequestView;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientOrderView clientOrderView;
    @Autowired
    private ClientRepository clientRepository;

    public ArrayList<ClientRequestView> getClientRequest(User user) {

        String[] result = requestRepository.getList(user.getUserID());
        return getRequestList(result);
    }

    private ArrayList<ClientRequestView> getRequestList(String[] objectList) {

        ArrayList<ClientRequestView> clientRequestQueries = new ArrayList<>();
        for (String raw : objectList) {
            clientRequestQueries.add(clientRequestView.setResult(raw));
        }
        return clientRequestQueries;
    }

    public ArrayList<ClientOrderView> getClientOrder(User user) {

        String[] result = orderRepository.getClientOrder(user.getUserID());
        return getOrderList(result);
    }

    private ArrayList<ClientOrderView> getOrderList(String[] objectList) {

        ArrayList<ClientOrderView> clientOrderQueries = new ArrayList<>();
        for (String o : objectList) {
            clientOrderQueries.add(clientOrderView.setResult(o));
        }
        return clientOrderQueries;
    }

    public double topUp(Long userID, Double chargeAmount) {
        return clientRepository.changeBalance(userID, chargeAmount);
    }

    public void insertRequest(User loggedUser, String persons, String roomClass, String dateFilter) {
        Date dateFromSQL = convertStringToSqlDate(dateFilter.split(":")[0].trim());
        Date dateToSQL = convertStringToSqlDate(dateFilter.split(":")[1].trim());
        requestRepository.insertRequest(loggedUser.getUserID(), persons, roomClass, dateFromSQL, dateToSQL, OrderStatus.WAITING_FOR_APPROVAL.name());
    }

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

    public void cancelRequest(String requestID) {
        requestRepository.deleteById(Integer.parseInt(requestID));
    }

    public User getClient(Long userID) {
        return clientRepository.getById(userID);
    }
}
