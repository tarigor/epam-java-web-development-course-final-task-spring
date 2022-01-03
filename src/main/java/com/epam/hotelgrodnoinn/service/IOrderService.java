package com.epam.hotelgrodnoinn.service;

import org.springframework.transaction.annotation.Transactional;

public interface IOrderService {
    @Transactional
    void payInvoice(long userID, int orderID, int requestID, int roomID, Double roomPrice);

    Double getRoomPrice(Integer roomID);
}
