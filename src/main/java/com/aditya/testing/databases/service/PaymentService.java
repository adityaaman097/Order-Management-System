package com.aditya.testing.databases.service;

import com.aditya.testing.databases.entity.Orders;
import com.aditya.testing.databases.entity.Payment;
import com.aditya.testing.databases.repo.OrderRepository;
import com.aditya.testing.databases.repo.PaymentRepository;
import com.aditya.testing.databases.utility.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Payment processPayment(String paymentMode, double paymentAmt, String order_id) {
        if (paymentAmt <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero.");
        }

        Payment payment = new Payment();
        payment.setAmt(paymentAmt);

        switch (paymentMode.toUpperCase()) {
            case "UPI":
                payment.setPaymentId(IdGenerator.paymentIdGenerator("upi_txn"));
                payment.setPaymentMode("UPI");
                payment.setPaymentStatus("Successful");
                break;
            case "CREDIT CARD":
            case "DEBIT CARD":
                payment.setPaymentId(IdGenerator.paymentIdGenerator("card_txn"));
                payment.setPaymentMode("Card");
                payment.setPaymentStatus("Successful");
                break;
            case "COD":
                payment.setPaymentId(IdGenerator.paymentIdGenerator("cod_"));
                payment.setPaymentStatus("Pending");
                payment.setPaymentMode("COD");
                break;
            default:
                payment.setPaymentMode("Unsupported");
                payment.setPaymentId(IdGenerator.paymentIdGenerator("error_"));
                throw new RuntimeException("Unsupported payment mode: " + paymentMode);
        }

        return paymentRepository.save(payment);
    }
}
