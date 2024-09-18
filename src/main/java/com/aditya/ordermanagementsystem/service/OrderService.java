package com.aditya.ordermanagementsystem.service;

import com.aditya.ordermanagementsystem.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public List<Order> getAllOrder();
    public Optional<Order> getOrderById(long id);
    public void saveOrder(Order o);
    public Order updatedOrder(Order o, long id);
    public Order updateOrderPaymentMode(String paymentMode, long id);
    public List<Order> searchOrders(String keyword, int page, int size);
}
