package com.epam.hotelgrodnoinn.repa;

import com.epam.hotelgrodnoinn.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query(value = "select request_id,persons_amount,room_class,check_in_date,check_out_date,request_status from request join client_request cr on request.request_id = cr.client_request_id where client_r_id = ?1", nativeQuery = true)
    String[] getList(Long userID);
}
