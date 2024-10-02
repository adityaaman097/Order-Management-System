package com.aditya.ordermanagementsystem.response.dto;

import com.aditya.ordermanagementsystem.entity.Order;

import java.time.LocalDateTime;

public class OrderResponseDTO {
    private long orderId;
    private ProductDetails productDetails;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private double totalWeight;
    private PaymentDetails paymentDetails;
    private String orderStatus;

    public OrderResponseDTO(Order order) {
        this.orderId = order.getOrderId();
        this.productDetails = new ProductDetails(order.getTitle(), order.getDescription(), order.getBrand(), order.getPrice(), order.getTotalWeight(), order.getFeedback());
        this.createdAt = order.getCreatedAt();
        this.updatedAt = order.getUpdatedAt();
        this.totalWeight = order.getTotalWeight();
        this.paymentDetails = new PaymentDetails(order.getPaymentMode(), order.getPaymentStatus());
        this.orderStatus = order.getOrderStatus();
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
