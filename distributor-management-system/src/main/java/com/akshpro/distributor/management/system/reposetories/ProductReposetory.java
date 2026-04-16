package com.akshpro.distributor.management.system.reposetories;


import com.akshpro.distributor.management.system.InformationPojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReposetory extends JpaRepository<ProductInfo, Long> {

}
