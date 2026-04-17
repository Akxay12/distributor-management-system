package com.akshpro.distributor.management.system.Services;

import com.akshpro.distributor.management.system.InformationPojo.DealerInfo;
import com.akshpro.distributor.management.system.InformationPojo.OrderInfo;
import com.akshpro.distributor.management.system.InformationPojo.ProductInfo;
import com.akshpro.distributor.management.system.dto.OrderResponse;
import com.akshpro.distributor.management.system.reposetories.DealerRepository;
import com.akshpro.distributor.management.system.reposetories.OrdersRepository;
import com.akshpro.distributor.management.system.reposetories.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrderService {


    // the entity class named = OrderInfo
    // have a object named order (most of the time )

    private final OrdersRepository orderBase;
    private final DealerRepository dealerRepository;
    private final ProductRepository productRepository;

    public OrderService(OrdersRepository orderBase,
                        DealerRepository dealerRepository,
                        ProductRepository productRepository) {
        this.orderBase = orderBase;
        this.dealerRepository = dealerRepository;
        this.productRepository = productRepository;
    }


    //====================== ========================================================


    // 🔹 create order
    public OrderInfo createOrder(String dealerName, String productName, int quantity, LocalDateTime orderDate){

        DealerInfo dealer = dealerRepository.findByName(dealerName)
                .orElseThrow(() -> new RuntimeException("Dealer not found"));

        ProductInfo product = productRepository.findByName(productName)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        OrderInfo order = new OrderInfo();
        order.setDealer(dealer);
        order.setProduct(product);
        order.setQuantity(quantity);

        if(orderDate != null){
            order.setOrderDate(orderDate);
        }

        return orderBase.save(order);
    }




    //🔹 view all orders
    public List<OrderInfo> viewAllOrders(){
        return orderBase.findAll();
    }

    //🔹 view all orders of one User
    public List<OrderResponse> getOrdersByDealer(String dealerName){

        List<OrderInfo> orders = orderBase.findByDealer_Name(dealerName);

        return orders.stream()
                .map(order -> new OrderResponse(
                        order.getId(),
                        order.getProduct().getName(),
                        order.getQuantity(),
                        order.getOrderDate()
                ))
                .toList();
    }
}
