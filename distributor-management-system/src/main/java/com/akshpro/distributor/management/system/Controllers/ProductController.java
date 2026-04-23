package com.akshpro.distributor.management.system.Controllers;


import com.akshpro.distributor.management.system.InformationPojo.ProductInfo;
import com.akshpro.distributor.management.system.Services.ProductService;
import com.akshpro.distributor.management.system.dto.ProductRequest;
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
    public ResponseEntity<ProductInfo> createProduct(@RequestBody ProductRequest request){

        ProductInfo savedproduct = Pservice.createProduct(request);

        return ResponseEntity.status(201).body(savedproduct);
    }

    // view all products
    @GetMapping("/viewall")
    public ResponseEntity<List<ProductInfo>> viewAllproducts(){
        List<ProductInfo> allproducts =  Pservice.viewAll();
        return ResponseEntity.ok(allproducts);
    }

    // view perticular product
    @GetMapping("/{id}")
    public ResponseEntity<ProductInfo> getProductById(@PathVariable long id){
        ProductInfo product = Pservice.viewById(id);
        if(product != null){
            return  ResponseEntity.ok(product);
        }
        return ResponseEntity.noContent().build();
    }



    // update product  price
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductInfo> updatePrice(@RequestBody ProductInfo  updatedInfo, @PathVariable long id){

        Optional<ProductInfo> updatedEntry = Pservice.updateProduct(updatedInfo,id);

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


    // view product by name
    @GetMapping("/name/{name}")
    public ResponseEntity<ProductInfo> getProductByName(@PathVariable String name){
        return ResponseEntity.ok(Pservice.getProductByName(name));

    }






}
