package com.aditya.ordermanagementsystem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "`order`")  // declare table name here under the back tick because order is reserve keyword in database
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long orderId;
        private String title;
        private String description;
        private LocalDateTime createdAt;
        private String brand;
        private double price;
        private LocalDateTime updatedAt;
        private int quantity;
        private double totalWeight;
        private String paymentMode;
        private String paymentStatus;
        private String feedback;
        private String orderStatus;

    // Constructor
    public Order() {
    }

    public Order(long orderId, String title, String description, LocalDateTime createdAt, String brand, double price, LocalDateTime updatedAt, int quantity, double totalWeight, String paymentMode, String paymentStatus, String feedback, String orderStatus) {
        this.orderId = orderId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.brand = brand;
        this.price = price;
        this.updatedAt = updatedAt;
        this.quantity = quantity;
        this.totalWeight = totalWeight;
        this.paymentMode = paymentMode;
        this.paymentStatus = paymentStatus;
        this.feedback = feedback;
        this.orderStatus = orderStatus;
    }

    // Setter

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    // Getter

    public long getOrderId() {
        return orderId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}
