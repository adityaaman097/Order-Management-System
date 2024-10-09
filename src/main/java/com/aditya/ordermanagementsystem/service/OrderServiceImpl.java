package com.aditya.ordermanagementsystem.service;

import com.aditya.ordermanagementsystem.entity.Order;
import com.aditya.ordermanagementsystem.repository.OrderRepository;
import com.aditya.ordermanagementsystem.threads.UpdateOrderTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Override
    public void placeOrder(Order o) {
        if (o.getPrice() < 0){
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (o.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (o.getTitle() == null || o.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Order title cannot be empty.");
        }
        if (o.getCreatedAt() == null) {
            o.setCreatedAt(LocalDateTime.now());
        }
        o.setUpdatedAt(LocalDateTime.now());
        o.setOrderStatus("CONFIRMED");
        orderRepository.save(o);
    }

    @Override
    public Future<Order> updateOrder(Order o, long id) {
       Future<Order> result =  executorService.submit(new UpdateOrderTask(id, o, orderRepository));
        try {
            return (Future<Order>) result.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Order updateOrderPaymentMode(String paymentMode, long id)  {
        try {
            Order order = orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order not found with id : "+id));
            order.setPaymentMode(paymentMode);
            order.setUpdatedAt(LocalDateTime.now());
            return orderRepository.save(order);
        }catch (Exception e){
            throw new RuntimeException("Failed to update order with id : " + e.getMessage());
        }
    }

    @Override
    public List<Order> searchOrders(String keyword, int page, int size) {
        if (page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero");
        } else if (size < 1) {
            throw new IllegalArgumentException("Page size must not be less than one");
        } else {
            Pageable p = PageRequest.of(page, size);
            Page<Order> pagePost = orderRepository.findByKeywordContaining(keyword, p);
            return pagePost.getContent();
        }
    }

    public List<Order> searchOrderByPriceRange(double minPrice, double maxPrice, int page, int size){
        page = page*size;
        if (minPrice < 0 || minPrice > maxPrice || maxPrice >= Double.MAX_VALUE){
            throw new IllegalArgumentException("Please Give the Valid Price Range");
        }
        if (page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero");
        } else if (size < 1) {
            throw new IllegalArgumentException("Page size must not be less than one");
        }
       return orderRepository.searchOrderByPriceRange(minPrice, maxPrice, page, size);
    }

    public List<Order> filterOrdersByFeedback(String feedback, int page, int size) {
        page = page*size;
        return orderRepository.findByFeedbackContaining(feedback, page, size);
    }

    public List<Order> filterOrdersByBrand(String brand, int page, int size) {
        page = page*size;
        return orderRepository.findByBrand(brand, page, size);
    }

    public List<Order> filterOrders(double minPrice, double maxPrice, String feedback, String brand, int page, int size) {
        page = page*size;
        return orderRepository.findByPriceBetweenAndFeedbackContainingAndBrand(minPrice, maxPrice, feedback, brand, page, size);
    }

    public Order getOrderByID(Long orderId){
        return orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);

    }
}
