package com.akshpro.distributor.management.system.Controllers;

import com.akshpro.distributor.management.system.InformationPojo.OrderInfo;
import com.akshpro.distributor.management.system.Services.OrderService;
import com.akshpro.distributor.management.system.dto.OrderRequest;
import com.akshpro.distributor.management.system.dto.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {


    private final OrderService orderService;


    public OrderController(OrderService orderService, OrderService orderService1) {
        this.orderService = orderService1;
    }

    //================ ========================================================


    // Add order
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request){

        return ResponseEntity.ok(
                orderService.createOrder(
                        request.getDealerName(),
                        request.getProductName(),
                        request.getQuantity(),
                        request.getOrderDate()
                )
        );
    }


    // view All Orders
    @GetMapping("/view")
    public ResponseEntity<List<OrderInfo>> vieworder() {
        List<OrderInfo> allOrders = orderService.viewAllOrders();
        return ResponseEntity.ok(allOrders);
    }


    // All orders of Perticular User
    @GetMapping("/dealer/{name}")
    public ResponseEntity<List<OrderResponse>> getOrdersByDealer(@PathVariable String name){

        return ResponseEntity.ok(orderService.getOrdersByDealer(name));
    }


}

