package com.akshpro.distributor.management.system.Services;


import com.akshpro.distributor.management.system.InformationPojo.ProductInfo;
import com.akshpro.distributor.management.system.reposetories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    // ProductInfo = entity class
    //objects are mostly name as product (Read carefullly)

    private final ProductRepository productbase;

    public ProductService(ProductRepository repo){
        productbase = repo;
    }

//=================================================


//    //🔹 save changes
//    public void save(ProductInfo info ){
//        productbase.save(info);
//    }

    //🔹 create product
    public ProductInfo createProduct(ProductInfo product){
       return  productbase.save(product);
    }

    //🔹 view all products
    public List<ProductInfo> viewAll(){
       return productbase.findAll();
    }

//    🔹 view product by id
    public ProductInfo viewById(long id){
        Optional<ProductInfo> info = productbase.findById(id);
        return info.orElse(null);
    }


    //🔹 upadte Product
    public Optional<ProductInfo> updateProduct(ProductInfo updatedInfo){
        long id = updatedInfo.getId();

        Optional<ProductInfo> info = productbase.findById(id);
        if(info.isPresent()){
           ProductInfo toupdate = info.get();
           toupdate.setPrice(updatedInfo.getPrice());
           toupdate.setName(updatedInfo.getName());

           productbase.save(toupdate);
           return Optional.of(toupdate);
        }
        return Optional.empty();
    }


    //🔹 delete Product
    public Boolean deleteProduct(long id){
        if(productbase.existsById(id)){
            productbase.deleteById(id);
            return true;
        }
        return false;
    }


    //🔹 get product by name
    public Optional<ProductInfo> getProductByName(String name){
        return productbase.findByName(name);

    }


}
