package com.akshpro.distributor.management.system.reposetories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.akshpro.distributor.management.system.InformationPojo.DealerInfo;

public interface DealerRepository extends JpaRepository<DealerInfo, Long> {
}

