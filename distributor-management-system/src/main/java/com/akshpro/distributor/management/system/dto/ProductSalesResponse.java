package com.akshpro.distributor.management.system.dto;

public class ProductSalesResponse {



        private String productName;
        private int totalQuantity;
        private double totalRevenue;

        public ProductSalesResponse(String productName, int totalQuantity, double totalRevenue) {
            this.productName = productName;
            this.totalQuantity = totalQuantity;
            this.totalRevenue = totalRevenue;
        }

    public String getProductName() {
        return productName;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    // getters


}
