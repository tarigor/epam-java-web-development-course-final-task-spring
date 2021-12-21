package com.epam.hotelgrodnoinn.service.impl;

import com.epam.hotelgrodnoinn.dao.ClientOrderDAO;
import com.epam.hotelgrodnoinn.dao.ClientRequestDAO;
import com.epam.hotelgrodnoinn.entity.ClientOrderView;
import com.epam.hotelgrodnoinn.entity.ClientRequestView;
import com.epam.hotelgrodnoinn.entity.User;
import com.epam.hotelgrodnoinn.repa.ClientRepository;
import com.epam.hotelgrodnoinn.repa.RequestRepository;
import com.epam.hotelgrodnoinn.types.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class ClientServiceImpl {

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientRequestDAO clientRequestDAO;
    @Autowired
    private ClientOrderDAO clientOrderDAO;

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

    public List<ClientRequestView> getClientRequest(User user) {
        return clientRequestDAO.getClientRequest(user.getUserID());
    }

    public List<ClientOrderView> getClientOrder(User user) {
        return clientOrderDAO.getClientOrder(user.getUserID());
    }

    public double topUp(Long userID, Double chargeAmount) {
        return clientRepository.changeBalance(userID, chargeAmount);
    }

    public void insertRequest(User loggedUser, String persons, String roomClass, String dateFilter) {
        Date dateFromSQL = convertStringToSqlDate(dateFilter.split(":")[0].trim());
        Date dateToSQL = convertStringToSqlDate(dateFilter.split(":")[1].trim());
        requestRepository.insertRequest(loggedUser.getUserID(), persons, roomClass, dateFromSQL, dateToSQL, OrderStatus.WAITING_FOR_APPROVAL.name());
    }

    public void cancelRequest(String requestID) {
        requestRepository.deleteById(Integer.parseInt(requestID));
    }

    public User getClient(Long userID) {
        return clientRepository.getById(userID);
    }
}
