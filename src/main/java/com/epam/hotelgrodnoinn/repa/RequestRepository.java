package com.epam.hotelgrodnoinn.repa;

import com.epam.hotelgrodnoinn.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    @Query(value = "call insert_new_request(?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    void insertRequest(long userID, String persons, String roomClass, Date dateFromSQL, Date dateToSQL, String name);
}
