package com.akshpro.distributor.management.system.Services;


import com.akshpro.distributor.management.system.Exeptions.BadRequestException;
import com.akshpro.distributor.management.system.Exeptions.ResourceNotFoundException;
import com.akshpro.distributor.management.system.InformationPojo.ProductInfo;
import com.akshpro.distributor.management.system.dto.ProductRequest;
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

//       create new product
    public ProductInfo createProduct(ProductRequest request){

        if(request.getName() == null || request.getName().trim().isEmpty()){
            throw new BadRequestException("Product name cannot be empty");
        }

        if(request.getPrice() <= 0){
            throw new BadRequestException("Price must be greater than zero");
        }

        ProductInfo product = new ProductInfo();

        product.setName(request.getName());
        product.setPrice(request.getPrice());

        return productbase.save(product);
    }

    //🔹 view all products
    public List<ProductInfo> viewAll(){
       return productbase.findAll();
    }

//    🔹 view product by id
public ProductInfo viewById(long id){
    return productbase.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
}


    //🔹 upadte Product
    public Optional<ProductInfo> updateProduct(ProductInfo updatedInfo,long id){
        if(updatedInfo.getPrice() <= 0){
            throw new BadRequestException("Price must be greater than zero");
        }

        if(updatedInfo.getName() == null || updatedInfo.getName().trim().isEmpty()){
            throw new BadRequestException("Product name cannot be empty");
        }

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
        if(!productbase.existsById(id)){
            throw new ResourceNotFoundException("Prodeuct not found!");
        }
        productbase.deleteById(id);
        return true;
    }


    //🔹 get product by name
    public ProductInfo getProductByName(String name){
        return productbase.findByName(name)
                .orElseThrow(()-> new ResourceNotFoundException("No product Found🫤"));


    }


}
