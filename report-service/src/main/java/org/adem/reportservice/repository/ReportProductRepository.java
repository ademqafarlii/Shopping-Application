package org.adem.reportservice.repository;

import org.adem.reportservice.model.ReportProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportProductRepository extends JpaRepository<ReportProduct,Integer> {
    Integer countByProductId(Integer id);
}
