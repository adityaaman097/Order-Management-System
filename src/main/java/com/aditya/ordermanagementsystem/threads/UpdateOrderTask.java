package com.aditya.ordermanagementsystem.threads;

import com.aditya.ordermanagementsystem.entity.Order;
import com.aditya.ordermanagementsystem.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.Callable;


public class UpdateOrderTask implements Callable<Order> {

    private long id;
    private Order order;
    private OrderRepository orderRepository;

    public UpdateOrderTask(long id, Order order, OrderRepository orderRepository) {
        this.id = id;
        this.order = order;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order call() {
            Order existingOrder = orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order is not found with id : "+id));
            existingOrder.setUpdatedAt(LocalDateTime.now());
            existingOrder.setTitle(order.getTitle());
            existingOrder.setDescription(order.getDescription());
            existingOrder.setBrand(order.getBrand());
            existingOrder.setPrice(order.getPrice());
            existingOrder.setPaymentMode(order.getPaymentMode());
            existingOrder.setQuantity(order.getQuantity());
            existingOrder.setTotalWeight(order.getTotalWeight());
            existingOrder.setPaymentStatus(order.getPaymentStatus());
            existingOrder.setBrand(order.getBrand());
            existingOrder.setFeedback(order.getFeedback());
            existingOrder.setOrderStatus("UPDATED");
            return orderRepository.save(existingOrder);
    }
}
