package com.aditya.ordermanagementsystem.threads;

import com.aditya.ordermanagementsystem.entity.Order;
import com.aditya.ordermanagementsystem.repository.OrderRepository;

public class PlaceOrderTask implements Runnable{
    private Order order;
    private OrderRepository orderRepository;

    public PlaceOrderTask(Order o, OrderRepository orderRepository){
        this.order = o;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run() {
        orderRepository.save(order);
    }
}
