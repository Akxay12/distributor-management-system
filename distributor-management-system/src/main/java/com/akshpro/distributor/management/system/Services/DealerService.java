package com.akshpro.distributor.management.system.Services;

import com.akshpro.distributor.management.system.Exeptions.BadRequestException;
import com.akshpro.distributor.management.system.Exeptions.ResourceNotFoundException;
import com.akshpro.distributor.management.system.InformationPojo.DealerInfo;
import com.akshpro.distributor.management.system.reposetories.DealerRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DealerService {


    private final DealerRepository database;

    public DealerService(DealerRepository database) {
        this.database = database;
    }

//    ==========================================================



    //🔹 create user
    public DealerInfo createDealer(DealerInfo dealer){
        if(dealer.getName() == null || dealer.getName().trim().isEmpty()){
            throw new BadRequestException("Dealer name cannot be empty");
        }

        if(dealer.getPhone() == null || dealer.getPhone().length() < 10){
            throw new BadRequestException("Invalid phone number");
        }

        if(database.existsByPhone(dealer.getPhone())){
            throw new BadRequestException("Dealer with this phone already exists");
        }

        return database.save(dealer);
    }


    // 🔹 Get All
    public List<DealerInfo> getAllDealers(){
        return database.findAll();
    }


    //🔹get user by id
    public Optional<DealerInfo> getUserById(long id){
        if(!database.existsById(id)){
            throw new ResourceNotFoundException("No dealer with Id "+id);
        }
        return database.findById(id);
    }


    //🔹update user
    public Optional<DealerInfo> updateUser(DealerInfo info,long id){


        if(info.getName() == null || info.getName().trim().isEmpty()){
            throw new BadRequestException("Dealer name cannot be empty");
        }

        if(info.getPhone() == null || info.getPhone().length() < 10){
            throw new BadRequestException("Invalid phone number");
        }

           Optional<DealerInfo> responsedealer = database.findById(id);

           if(!responsedealer.isPresent()) {
               throw new ResourceNotFoundException("No user present with id "+id);
           }


               DealerInfo dealer = responsedealer.get();

               dealer.setName(info.getName());
               dealer.setShopname(info.getShopname());
               dealer.setCity(info.getCity());
               dealer.setPhone(info.getPhone());

               database.save(dealer);
               return Optional.of(dealer);

    }


    //🔹delete user  (BY ID)
    public void deleteUserbyId(long id){

        Optional<DealerInfo> deletedentry = database.findById(id);
        if(deletedentry.isPresent()){
            database.deleteById(id);

        }else{
            throw new ResourceNotFoundException("No user with id "+id);
        }
    }


    //🔹find User (BY ID)
    public DealerInfo findUserByName(String name){
        return database.findByName(name)
                .orElseThrow(()-> new ResourceNotFoundException("Dealer Not Found!"));
    }


    //get dealer by city
    public List<DealerInfo> getdealerByCity(String city){
        if(city == null || city.trim().isEmpty()){
            throw new BadRequestException("City cannot be empty");
        }
        return database.findByCityIgnoreCase(city);
    }



}
