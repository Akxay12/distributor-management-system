package com.akshpro.distributor.management.system.InformationPojo;


import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_base")
public class OrderInfo {




        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "dealer_id", nullable = false)
        private DealerInfo dealer;

        @ManyToOne
        @JoinColumn(name = "product_id", nullable = false)
        private ProductInfo product;

        private int quantity;

        private LocalDateTime orderDate;

        @PrePersist
        public void setOrderDate() {
            if (this.orderDate == null) {
                this.orderDate = LocalDateTime.now();
            }
        }


    // ========================================================
        // getters & setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DealerInfo getDealer() {
        return dealer;
    }

    public void setDealer(DealerInfo dealer) {
        this.dealer = dealer;
    }

    public ProductInfo getProduct() {
        return product;
    }

    public void setProduct(ProductInfo product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
