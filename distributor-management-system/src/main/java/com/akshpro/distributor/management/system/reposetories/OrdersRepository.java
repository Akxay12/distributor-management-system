package com.akshpro.distributor.management.system.reposetories;

import com.akshpro.distributor.management.system.InformationPojo.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrdersRepository extends JpaRepository<OrderInfo, Long> {
    List<OrderInfo> findByDealer_Name(String name);

    List<OrderInfo> findByDealer_Id(long id);

    List<OrderInfo> findByProduct_NameIgnoreCase(String name);
    // hi
    List<OrderInfo> findByProduct_Id(long id);

    List<OrderInfo> findTop10ByOrderByOrderDateDesc();
}
