package com.akshpro.distributor.management.system.Services;

import com.akshpro.distributor.management.system.InformationPojo.DealerInfo;
import com.akshpro.distributor.management.system.reposetories.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealerService {


    private final DealerRepository database;

    public DealerService(DealerRepository database) {
        this.database = database;
    }

//    ==========================================================

    public void saveUser(DealerInfo dealer){
        database.save(dealer);
    }


    //🔹 create user
    public DealerInfo createDealer(DealerInfo dealer){
        return database.save(dealer);
    }


    // 🔹 Get All
    public List<DealerInfo> getAllDealers(){
        return database.findAll();
    }


    //🔹get user by id
    public Optional<DealerInfo> getUserById(long id){
        return database.findById(id);
    }


    //🔹update user
    public Optional<DealerInfo> updateUser(DealerInfo info){
        long id = info.getId();
           Optional<DealerInfo> responsedealer = database.findById(id);

           if(responsedealer.isPresent()) {
               DealerInfo dealer = responsedealer.get();

               dealer.setName(info.getName());
               dealer.setShopname(info.getShopname());
               dealer.setAddress(info.getAddress());
               dealer.setPhone(info.getPhone());

               saveUser(dealer);
               return Optional.of(dealer);
           }
       return Optional.empty();
    }


    //🔹delete user  (BY ID)
    public Boolean deleteUserbyId(long id){

        Optional<DealerInfo> deletedentry = database.findById(id);
        if(deletedentry.isPresent()){
            database.deleteById(id);
            return true;
        }
        return false;
    }


}
