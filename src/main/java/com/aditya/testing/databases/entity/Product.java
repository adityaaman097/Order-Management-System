package com.aditya.testing.databases.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    private String productId;
    private String name;
    private String category;
    private String description;
    private String brand;
    private double weight;
    private double price;
    private String feedback;
    private int orderedItemCount;
    private int stockQuantity;

    public Product() {
    }

    public Product(String productId, String name, String category, String description, String brand, double weight, double price, String feedback, int stockQuantity, int orderedItemCount) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.description = description;
        this.brand = brand;
        this.weight = weight;
        this.price = price;
        this.feedback = feedback;
        this.orderedItemCount = orderedItemCount;
        this.stockQuantity = stockQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getOrderedItemCount() {
        return orderedItemCount;
    }

    public void setOrderedItemCount(int orderedItemCount) {
        this.orderedItemCount = orderedItemCount;
    }
}
