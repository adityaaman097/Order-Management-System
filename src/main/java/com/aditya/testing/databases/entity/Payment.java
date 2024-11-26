package com.aditya.testing.databases.entity;

import com.aditya.testing.databases.utility.IdGenerator;
import jakarta.persistence.*;

@Entity
public class Payment {
    @Id
    private String paymentId;
    private String paymentMode;
    private String paymentStatus;
    private double amt;


    public Payment() {
    }

    public Payment(String paymentId, String paymentMode, String paymentStatus, double amt, Orders orders) {
        this.paymentId = paymentId;
        this.paymentMode = paymentMode;
        this.paymentStatus = paymentStatus;
        this.amt = amt;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }
}
