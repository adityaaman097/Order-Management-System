package com.aditya.ordermanagementsystem.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    private String title;
    private String description;
    private double price;
    private String paymentMode;

    // Automatically stores the timestamp when the order is created
    @CreationTimestamp
    private LocalDateTime createdAt;

    // Automatically updates the timestamp when the order is modified
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    public Order() {
    }

    public Order(long orderId, String title, String description, double price, String paymentMode, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.paymentMode = paymentMode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
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
}
