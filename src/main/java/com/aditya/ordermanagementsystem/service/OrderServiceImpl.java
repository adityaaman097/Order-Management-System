package com.aditya.ordermanagementsystem.service;

import com.aditya.ordermanagementsystem.entity.Order;
import com.aditya.ordermanagementsystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void saveOrder(Order o) {
        orderRepository.save(o);
    }

    @Override
    public Order updatedOrder(Order o, long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found with id : "+id));
        order.setTitle(o.getTitle());
        order.setDescription(o.getDescription());
        order.setPaymentMode(o.getPaymentMode());
        order.setPrice(o.getPrice());
        return orderRepository.save(order);
    }
    @Override
    public Order updateOrderPaymentMode(String paymentMode, long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order not found with id : "+id));
        order.setPaymentMode(paymentMode);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> searchOrders(String keyword, int page, int size) {
        int offset = (page - 1) * size;
        return orderRepository.searchOrdersWithLimit(keyword, size, offset);
    }
}
