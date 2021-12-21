package com.epam.hotelgrodnoinn.service.impl;

import com.epam.hotelgrodnoinn.dao.OrderDAO;
import com.epam.hotelgrodnoinn.repa.ClientRepository;
import com.epam.hotelgrodnoinn.repa.RoomRepository;
import com.epam.hotelgrodnoinn.types.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderServiceImpl {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private OrderDAO orderDAO;

    @Transactional
    public void payInvoice(long userID, int orderID, int requestID, int roomID, Double roomPrice) {

        double changeValue = roomPrice * (-1);
        clientRepository.changeBalance(userID, changeValue);
        orderDAO.changeStatusOfOrder(OrderStatus.PAID_AND_BOOKED.name(), orderID, requestID, roomID);
    }

    public Double getRoomPrice(Integer roomID) {

        Double roomPrice = 0.0;
        if (roomRepository.findById(roomID).isPresent()) {
            roomPrice = roomRepository.findById(roomID).get().getRoomCost();
        }
        return roomPrice;
    }
}
