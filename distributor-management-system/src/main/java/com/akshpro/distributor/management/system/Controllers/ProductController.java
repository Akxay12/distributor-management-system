package com.akshpro.distributor.management.system.Controllers;


import com.akshpro.distributor.management.system.InformationPojo.ProductInfo;
import com.akshpro.distributor.management.system.Services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService Pservice;

    public ProductController(ProductService service){
        Pservice = service;
    }

// ======================== =================================



    // create entry of new products
    @PostMapping("/create")
    public ResponseEntity<ProductInfo> createProduct(@RequestBody ProductInfo product){
        ProductInfo savedproduct = Pservice.createProduct(product);

        if(savedproduct !=null){
            return ResponseEntity.status(201).body(savedproduct);
        }
        return ResponseEntity.badRequest().build();
    }

    // view all products
    @GetMapping("/view")
    public ResponseEntity<List<ProductInfo>> viewAllproducts(){
        List<ProductInfo> allproducts =  Pservice.viewAll();
        return ResponseEntity.ok(allproducts);
    }

    // update product  price
    @PutMapping("/update")
    public ResponseEntity<ProductInfo> updatePrice(@RequestBody ProductInfo  updatedInfo){

        Optional<ProductInfo> updatedEntry = Pservice.updateProduct(updatedInfo);

       return updatedEntry.map(ResponseEntity::ok)
               .orElseGet(()->ResponseEntity.notFound().build());

    }

    // delete product
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long  id){
        Boolean deletedornot = Pservice.deleteProduct(id);
        if(deletedornot){
            return ResponseEntity.ok("Product with ID: "+id+" is Deleted✅");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No product with ID "+id+" found!");
    }

}
