package com.aditya.testing.databases.dto;

import com.aditya.testing.databases.entity.Product;

import java.util.List;

public class OrderReq {
    private List<OrderProduct> orderProducts;
    private String address;
    private String paymentMode;
    private double paymentAmt;
    private String additionalInfo;

    public OrderReq() {
    }

    public OrderReq(List<OrderProduct> orderProducts, String address, String paymentMode, double paymentAmt, String additionalInfo) {
        this.orderProducts = orderProducts;
        this.address = address;
        this.paymentMode = paymentMode;
        this.paymentAmt = paymentAmt;
        this.additionalInfo = additionalInfo;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public double getPaymentAmt() {
        return paymentAmt;
    }

    public void setPaymentAmt(double paymentAmt) {
        this.paymentAmt = paymentAmt;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
