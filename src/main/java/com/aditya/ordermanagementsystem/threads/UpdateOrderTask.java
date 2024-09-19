package com.aditya.ordermanagementsystem.threads;

import com.aditya.ordermanagementsystem.entity.Order;
import com.aditya.ordermanagementsystem.repository.OrderRepository;

import java.util.concurrent.CompletableFuture;

public class UpdateOrderTask implements Runnable{

    private long id;
    private Order order;
    private OrderRepository orderRepository;
    private CompletableFuture<String> result;

    public UpdateOrderTask(long id, Order order, OrderRepository orderRepository, CompletableFuture result) {
        this.id = id;
        this.order = order;
        this.orderRepository = orderRepository;
        this.result = result;
    }

    @Override
    public void run() {
        try{
            Order existingOrder = orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order is not found with id : "+id));
            existingOrder.setTitle(order.getTitle());
            existingOrder.setDescription(order.getDescription());
            existingOrder.setPaymentMode(order.getPaymentMode());
            existingOrder.setPrice(order.getPrice());
            orderRepository.save(existingOrder);
            result.complete("Order Updated Successfully with id : " + id);
        }catch (Exception e){
            result.completeExceptionally(e);
        }
    }
}
