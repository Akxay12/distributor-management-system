package com.akshpro.distributor.management.system.reposetories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.akshpro.distributor.management.system.InformationPojo.DealerInfo;

import java.util.List;
import java.util.Optional;

public interface DealerRepository extends JpaRepository<DealerInfo, Long> {

    Optional<DealerInfo> findByName(String name);  // ✔ only declaration

    List<DealerInfo> findByCityIgnoreCase(String city);
}


