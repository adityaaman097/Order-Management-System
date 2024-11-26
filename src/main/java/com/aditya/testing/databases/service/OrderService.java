package com.aditya.testing.databases.service;


import com.aditya.testing.databases.dto.OrderReq;
import com.aditya.testing.databases.entity.Orders;
import com.aditya.testing.databases.entity.Payment;
import com.aditya.testing.databases.entity.Product;
import com.aditya.testing.databases.repo.OrderRepository;
import com.aditya.testing.databases.repo.ProductRepository;
import com.aditya.testing.databases.dto.OrderProduct;
import com.aditya.testing.databases.utility.IdGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;


    @Transactional
    public void createOrder(OrderReq orderReq){
        double totalPrice = 0.00;
        double totalWeight = 0.00;
        int item = orderReq.getOrderProducts().size();
        LocalDateTime now = LocalDateTime.now();

        List<Product> allProducts = new ArrayList<>();
        List<OrderProduct> prodIds = orderReq.getOrderProducts();

        // Loop through products to calculate total price and weight
        for (OrderProduct products : prodIds){
            Product product = productRepository.findById(products.getProductId()).orElseThrow(() -> new RuntimeException("Product Not Found"));
            if (products.getQuantity() > product.getStockQuantity()){
                throw new RuntimeException("Given Quantity : "+products.getQuantity()+" is not available");
            }
            product.setOrderedItemCount(products.getQuantity());
            totalPrice = totalPrice + products.getQuantity() * product.getPrice();
            totalWeight = totalWeight + products.getQuantity() * product.getWeight();
            allProducts.add(product);
            product.setStockQuantity(product.getStockQuantity()-products.getQuantity());
        }
        // Create and save the order
        Orders o = new Orders();
        o.setOrderId(IdGenerator.generateOrderId());
        o.setCreatedAt(LocalDateTime.now());
        o.setTotalPrice(totalPrice);
        o.setTotalWeight(totalWeight);
        o.setExpDeliveryDate(LocalDateTime.now().plusDays(5));
        o.setUpdatedAt(LocalDateTime.now());
        o.setItemsAdded(item);
        o.setOrderStatus("Created");
        o.setProducts(allProducts);

       Payment payment =  paymentService.processPayment(orderReq.getPaymentMode(), orderReq.getPaymentAmt(), o.getOrderId());
       o.setPayment(payment);
        orderRepository.save(o);
    }
    public List<Orders> getAllOrder(){
        return orderRepository.findAll();
    }

    public void deleteOrderById(String id){
        orderRepository.deleteById(id);
    }
}
