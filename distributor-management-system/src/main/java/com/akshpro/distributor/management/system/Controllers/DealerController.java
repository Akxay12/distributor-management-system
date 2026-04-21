package com.akshpro.distributor.management.system.Controllers;

import com.akshpro.distributor.management.system.InformationPojo.DealerInfo;
import com.akshpro.distributor.management.system.Services.DealerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dealer")
public class DealerController {

    private final DealerService service;

    public DealerController(DealerService service) {
        this.service = service;
    }

//    =========================================================

    // create user Dealer
    @PostMapping("/create")
    public ResponseEntity<DealerInfo> create(@RequestBody DealerInfo dealer){
//        return service.createDealer(dealer);
       DealerInfo saved = service.createDealer(dealer);
       if(saved == null) {
           return ResponseEntity.badRequest().build();
       }
        return ResponseEntity.status(201).body(saved);
    }


    //get All users
    @GetMapping("/all")
    public ResponseEntity<List<DealerInfo>> getAll(){
//        return service.getAllDealers();
        return ResponseEntity.ok(service.getAllDealers());
    }


    // get user by id
    @GetMapping("/id={id}")
    public ResponseEntity<DealerInfo> specific_user(@PathVariable long id){
        Optional<DealerInfo> userbyid = service.getUserById(id);


        return userbyid.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    // update user
    @PutMapping("/update/{id}")
    public ResponseEntity<DealerInfo> updateUser(@RequestBody DealerInfo info,@PathVariable long id){
        Optional<DealerInfo> updatedealer = service.updateUser(info,id);

        return updatedealer
                .map(ResponseEntity::ok)   // ✔ updated object bhej raha hai
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //delete user (BY ID)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> DeleteUser(@PathVariable long id){
        Boolean deleteduser = service.deleteUserbyId(id);
        if(deleteduser){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }


    @GetMapping("/name/{name}")
    public ResponseEntity<DealerInfo> findByName(@PathVariable String name){

        return service.findUserByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
