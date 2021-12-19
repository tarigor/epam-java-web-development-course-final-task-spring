package com.epam.hotelgrodnoinn.repa;

import com.epam.hotelgrodnoinn.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    @Query(value = "select request_id,persons_amount,room_class,check_in_date,check_out_date,request_status from request join client_request cr on request.request_id = cr.client_request_id where client_r_id = ?1", nativeQuery = true)
    String[] getList(Long userID);

    @Query(value ="call insert_new_request(?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    void insertRequest(long userID, String persons, String roomClass, Date dateFromSQL, Date dateToSQL, String name);
}
