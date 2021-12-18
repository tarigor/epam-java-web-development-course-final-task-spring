package com.epam.hotelgrodnoinn.repa;

import com.epam.hotelgrodnoinn.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "SELECT client_order_id, request_id, room_id, room_class, check_in_date, check_out_date, order_status FROM orders o join client_order co on o.client_order_id = co.client_order_room_id " +
            "join room r on o.room_id = r.id " +
            "join room_class rc on r.room_class_id = rc.id " +
            "where co.client_id = ?1", nativeQuery = true)
    String[] getClientOrder(Long clientID);
}
