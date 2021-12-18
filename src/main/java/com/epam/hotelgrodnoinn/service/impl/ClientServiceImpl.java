package com.epam.hotelgrodnoinn.service.impl;

import com.epam.hotelgrodnoinn.entity.ClientOrderView;
import com.epam.hotelgrodnoinn.entity.ClientRequestView;
import com.epam.hotelgrodnoinn.entity.User;
import com.epam.hotelgrodnoinn.repa.OrderRepository;
import com.epam.hotelgrodnoinn.repa.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
