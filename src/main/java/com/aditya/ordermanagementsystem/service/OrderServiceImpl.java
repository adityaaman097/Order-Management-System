package com.aditya.ordermanagementsystem.service;

import com.aditya.ordermanagementsystem.entity.Order;
import com.aditya.ordermanagementsystem.repository.OrderRepository;
import com.aditya.ordermanagementsystem.threads.PlaceOrderTask;
import com.aditya.ordermanagementsystem.threads.UpdateOrderTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void placeOrder(Order o) {
        Thread thread = new Thread(new PlaceOrderTask(o, orderRepository));
        thread.start();
    }

    @Override
    public CompletableFuture<String> updateOrder(Order o, long id) {
        CompletableFuture<String> result = new CompletableFuture<>();
        Thread th = new Thread(new UpdateOrderTask(id,o,orderRepository, result));
        th.start();
        return result;
    }

    public Order updateOrderPaymentMode(String paymentMode, long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order not found with id : "+id));
        order.setPaymentMode(paymentMode);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> searchOrders(String keyword, int page, int size) {
        Pageable p = PageRequest.of(page, size);
        Page<Order> pagePost = this.orderRepository.findAll(p);
        return pagePost.getContent();
    }
}
