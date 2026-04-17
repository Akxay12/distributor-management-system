package com.akshpro.distributor.management.system.reposetories;


import com.akshpro.distributor.management.system.InformationPojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductInfo, Long> {
  Optional<ProductInfo> findByName(String name);  // ✔
}
