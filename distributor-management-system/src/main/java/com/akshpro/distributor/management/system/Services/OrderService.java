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
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService {


    // the entity class named = OrderInfo
    // have  object named order (most of the time )

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
    public OrderResponse createOrder(String dealerName, String productName, int quantity, LocalDateTime orderDate){

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



        //  total calculate

        double totalPrice = product.getPrice() * quantity;
        order.setOrderPrice(totalPrice);

        OrderInfo saved = orderBase.save(order);

        return new OrderResponse(
                saved.getId(),
                saved.getProduct().getName(),
                saved.getQuantity(),
                saved.getOrderPrice(),
                saved.getOrderDate()
        );
    }



    //🔹 view all orders
    public List<OrderInfo> viewAllOrders(){
        return orderBase.findAll();
    }

    //🔹 view all orders of one User
    public List<OrderResponse> getOrdersByDealer(long id){

        List<OrderInfo> orders = orderBase.findByDealer_Id(id);
        return orders.stream()
                .map(order -> {

                    double total = order.getQuantity() * order.getOrderPrice(); // 🔥 FIX

                    return new OrderResponse(
                            order.getId(),
                            order.getProduct().getName(),
                            order.getQuantity(),
                            order.getOrderPrice(),
                            order.getOrderDate()
                    );
                })
                .toList();
    }



    // delete order
    public Boolean deleteorder(long id){
        if(orderBase.existsById(id)){
            orderBase.deleteById(id);
            return true;
        }
        return false;
    }


}
