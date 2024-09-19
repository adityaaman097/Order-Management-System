package com.aditya.ordermanagementsystem.controller;

import com.aditya.ordermanagementsystem.entity.Order;
import com.aditya.ordermanagementsystem.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;


    @PostMapping("/order")
    public String placeOrder(@RequestBody Order order){
        orderService.placeOrder(order);
        return "Order Created Successfully";
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<?> updateOrder(@RequestBody Order order, @PathVariable long id){
        try {
            String result = orderService.updateOrder(order, id).get(5, TimeUnit.SECONDS); // Wait up to 5 seconds
            return ResponseEntity.ok(result);
        } catch (InterruptedException | ExecutionException e) {
            String errorMessage = "Error updating order with ID : " + id + " - " + e.getCause().getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } catch (TimeoutException e) {
            String errorMessage = "Update operation timed out for order with ID : " + id;
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(errorMessage);
        }
    }

    @PatchMapping("/order/{id}/payment-mode")
    public ResponseEntity<Order> updatePaymentMode(@PathVariable long id, @RequestParam String paymentMode) {
        try{
            Order updatedOrder = orderService.updateOrderPaymentMode(paymentMode, id);
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("order/search")
    public List<Order> searchPaginatedOrderByTitleDescription(@RequestParam String keyword,
                                                              @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                                              @RequestParam(value = "size", defaultValue = "2", required = false) int size){
        return orderService.searchOrders(keyword, page, size);
    }
}
