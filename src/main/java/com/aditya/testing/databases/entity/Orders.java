package com.aditya.testing.databases.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Orders {
    @Id
    private String orderId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private double totalPrice;
    private double totalWeight;
    private LocalDateTime expDeliveryDate;
    private String orderStatus;
    private int itemsAdded;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "orderID"),
    inverseJoinColumns = @JoinColumn(name = "productId"))
    private List<Product> products;


    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "paymentId", nullable = true)
    private Payment payment;

    public Orders() {
    }

    public Orders(String orderId, LocalDateTime createdAt, LocalDateTime updatedAt, double totalPrice, double totalWeight, LocalDateTime expDeliveryDate, String orderStatus, int itemsAdded, List<Product> products, Payment payment) {
        this.orderId = orderId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.totalPrice = totalPrice;
        this.totalWeight = totalWeight;
        this.expDeliveryDate = expDeliveryDate;
        this.orderStatus = orderStatus;
        this.itemsAdded = itemsAdded;
        this.products = products;
        this.payment = payment;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public LocalDateTime getExpDeliveryDate() {
        return expDeliveryDate;
    }

    public void setExpDeliveryDate(LocalDateTime expDeliveryDate) {
        this.expDeliveryDate = expDeliveryDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getItemsAdded() {
        return itemsAdded;
    }

    public void setItemsAdded(int itemsAdded) {
        this.itemsAdded = itemsAdded;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
