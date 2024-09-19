package com.aditya.ordermanagementsystem.service;

import com.aditya.ordermanagementsystem.entity.Order;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface OrderService {
    public void placeOrder(Order o);
    public CompletableFuture<String> updateOrder(Order o, long id);
    public List<Order> searchOrders(String keyword, int page, int size);
}
