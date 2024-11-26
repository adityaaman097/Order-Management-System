package com.aditya.testing.databases.repo;

import com.aditya.testing.databases.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends JpaRepository<Orders, String> {
    /*@Modifying
    @Query("DELETE FROM Order o WHERE o.id = :id")
    void deleteById(@Param("id")String id);*/

}