package com.akshpro.distributor.management.system.Services;

import com.akshpro.distributor.management.system.InformationPojo.DealerInfo;
import com.akshpro.distributor.management.system.reposetories.DealerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealerService {


    private final DealerRepository database;

    public DealerService(DealerRepository database) {
        this.database = database;
    }
    // 🔹 Create
    public DealerInfo createDealer(DealerInfo dealer){
        return database.save(dealer);
    }

    // 🔹 Get All
    public List<DealerInfo> getAllDealers(){
        return database.findAll();
    }
}
