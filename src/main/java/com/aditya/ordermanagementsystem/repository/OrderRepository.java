package com.aditya.ordermanagementsystem.repository;

import com.aditya.ordermanagementsystem.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM `order` o WHERE o.title LIKE %:keyword% OR o.description LIKE %:keyword%",
            nativeQuery = true)
    Page<Order> findByKeywordContaining(@Param("keyword") String Keyword, Pageable pageable);

    @Query(value = "SELECT * FROM `order` WHERE price between :minPrice AND :maxPrice LIMIT :size OFFSET :page",
            nativeQuery = true)
    List<Order> searchOrderByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice,
                                        @Param("page") int page, @Param("size") int size);

    @Query(value = "SELECT * FROM `order` WHERE feedback LIKE %:feedback%  LIMIT :size OFFSET :page",
            nativeQuery = true)
    List<Order> findByFeedbackContaining(@Param("feedback") String feedback, @Param("page") int page,
                                         @Param("size") int size);

    @Query(value = "SELECT * FROM `order` WHERE brand LIKE %:brand%  LIMIT :size OFFSET :page", nativeQuery = true)
    List<Order> findByBrand(@Param("brand") String brand, @Param("page") int page, @Param("size") int size);

    @Query(value = "SELECT * FROM `order` WHERE (price BETWEEN :min AND :max) OR (brand LIKE '%:brand%' OR feedback LIKE '%:feedback%') LIMIT :size OFFSET :page", nativeQuery = true)
    List<Order> findByPriceBetweenAndFeedbackContainingAndBrand(@Param("min") double minPrice, @Param("max") double maxPrice, @Param("feedback") String feedback, @Param("brand") String brand, @Param("page") int page, @Param("size") int size);
}
