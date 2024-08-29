package org.adem.reportservice.repository;

import org.adem.reportservice.model.ReportCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportCustomerRepository extends JpaRepository<ReportCustomer,Integer> {

    Integer countByReportedCustomer(String reportedCustomer);

}
