package com.aditya.ordermanagementsystem.response.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProductDetails {
    private String title;
    private String description;
    private String brand;
    private double price;
    private double productWeight;
    private String feedback;

    public ProductDetails( String title, String description, String brand, double price, double productWeight, String feedback) {
        this.title = title;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.productWeight = productWeight;
        this.feedback = feedback;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(double productWeight) {
        this.productWeight = productWeight;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
