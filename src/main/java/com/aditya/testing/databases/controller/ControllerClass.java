package com.aditya.testing.databases.controller;

import com.aditya.testing.databases.dto.OrderReq;
import com.aditya.testing.databases.entity.Orders;
import com.aditya.testing.databases.dto.OrderProduct;
import com.aditya.testing.databases.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerClass {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
public String placeOrder(@RequestBody OrderReq orderReq){
        orderService.createOrder(orderReq);
        return "Order Created Successfully";
    }

    @GetMapping("/orders")
    public List<Orders> getAllOrder(){
        return orderService.getAllOrder();
    }

    @DeleteMapping("/orders/{id}")
    public String deleteOrderById(@PathVariable String id){
        orderService.deleteOrderById(id);
        return "Order deleted Successfully";
    }
}
