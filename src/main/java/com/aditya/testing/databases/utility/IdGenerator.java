package com.aditya.testing.databases.utility;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

    private static final AtomicLong orderCounter = new AtomicLong(1);
    private static final AtomicLong productCounter = new AtomicLong(1);
    private static final AtomicLong paymentCounter = new AtomicLong(1);

    // Method to generate ID based on entity type and padding length
    public static String generateId(String prefix, AtomicLong counter, int paddingLength) {
        DecimalFormat df = new DecimalFormat("0".repeat(paddingLength));
        return prefix + df.format(counter.getAndIncrement());
    }

    public static String generateOrderId(){
        return generateId("ord_prod_", orderCounter, 8);
    }

    public static String generateProductId(){
        return generateId("prod_", productCounter, 8);
    }

    public static String paymentIdGenerator(String pref){
        return generateId(pref, paymentCounter, 8);
    }
}
