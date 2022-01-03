package com.epam.hotelgrodnoinn.service;

import com.epam.hotelgrodnoinn.entity.ClientOrderView;
import com.epam.hotelgrodnoinn.entity.ClientRequestView;
import com.epam.hotelgrodnoinn.entity.OrderInvoiceForm;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public interface IAdminService {
    List<ClientOrderView> getAllOrders();

    List<ClientRequestView> getAllRequestsForAdmin();

    ClientRequestView getClientRequest(String requestID, String email);

    @Transactional
    void insertNewOrder(String clientID, String requestID, String[] singleRoomsSelected, String[] doubleRoomsSelected, String[] suiteRoomsSelected, String[] deluxeRoomsSelected, String dateFrom, String dateTo);

    void addOrder(String[] roomsSelected, String dateFrom, String dateTo, ArrayList<OrderInvoiceForm> clientOrderRooms);

    void rejectRequest(int requestID);
}
