package com.akshpro.distributor.management.system.InformationPojo;


import jakarta.persistence.*;

@Entity
@Table(name = "product_info")
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;



//==============================================



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }





//    // 🔥 relation with DealerProduct (optional but recommended)
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    private java.util.List<DealerProduct> dealerProducts;


}
