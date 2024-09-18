package com.aditya.ordermanagementsystem.controller;

import com.aditya.ordermanagementsystem.entity.Order;
import com.aditya.ordermanagementsystem.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/order")
    public List<Order> getAllOrders(){
        return orderService.getAllOrder();
    }

    @GetMapping("/order/{id}")
    public Optional<Order> getOrderById(@PathVariable int id){
        return orderService.getOrderById(id);
    }

    @PostMapping("/order")
    public String placeOrder(@RequestBody Order order){
        orderService.saveOrder(order);
        return "Order Created Successfully";
    }

    @PutMapping("/order/{id}")
    public Order updateOrder(@RequestBody Order order, @PathVariable long id){
        return orderService.updatedOrder(order, id);
    }

    @PatchMapping("/order/{id}/payment-mode")
    public ResponseEntity<Order> updatePaymentMode(@PathVariable long id, @RequestParam String paymentMode) {
        Order updatedOrder = orderService.updateOrderPaymentMode(paymentMode, id);
        return ResponseEntity.ok(updatedOrder);
    }

    @GetMapping("order/search")
    public List<Order> searchPaginatedOrderByTitleDescription(@RequestParam String keyword, @RequestParam int page, @RequestParam int size){
        return orderService.searchOrders(keyword, page, size);
    }
}
