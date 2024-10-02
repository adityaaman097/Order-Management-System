package com.aditya.ordermanagementsystem.service;

import com.aditya.ordermanagementsystem.entity.Order;

import java.util.List;
import java.util.concurrent.Future;

public interface OrderService {
    public void placeOrder(Order o);
    public Future<Order> updateOrder(Order o, long id) throws Exception;
    public List<Order> searchOrders(String keyword, int page, int size);
}
