package com.epam.hotelgrodnoinn.service;

import com.epam.hotelgrodnoinn.entity.ClientOrderView;
import com.epam.hotelgrodnoinn.entity.ClientRequestView;
import com.epam.hotelgrodnoinn.entity.User;

import java.util.List;

public interface IClientService {
    List<ClientRequestView> getClientRequest(User user);

    List<ClientOrderView> getClientOrder(User user);

    double topUp(Long userID, Double chargeAmount);

    void insertRequest(User loggedUser, String persons, String roomClass, String dateFilter);

    void cancelRequest(String requestID);

    User getClient(Long userID);
}
