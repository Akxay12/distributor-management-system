package com.akshpro.distributor.management.system.dto;

import java.time.LocalDateTime;

public class OrderResponse {

    private Long orderId;
    private String productName;
    private int quantity;
    private double orderPrice;
    private LocalDateTime orderDate;

    public OrderResponse(Long orderId, String productName, int quantity, double orderPrice, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.productName = productName;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
        this.orderDate = orderDate;
    }

    // getters


    public Long getOrderId() {
        return orderId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public double getOrderPrice() {
        return orderPrice;
    }
}
