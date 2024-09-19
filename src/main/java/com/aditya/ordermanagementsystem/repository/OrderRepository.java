package com.aditya.ordermanagementsystem.repository;

import com.aditya.ordermanagementsystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
//    @Query(value = "SELECT * FROM `order` WHERE title LIKE %:keyword% OR description LIKE %:keyword% LIMIT :limit OFFSET :offset", nativeQuery = true)
//    List<Order> searchOrdersWithLimit(@Param("keyword") String keyword, @Param("limit") int limit, @Param("offset") int offset);

}
