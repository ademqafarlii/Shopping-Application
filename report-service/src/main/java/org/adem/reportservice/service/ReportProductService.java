package org.adem.reportservice.service;

import org.adem.reportservice.dto.ReportProductDto;
import org.adem.reportservice.dto.ReportProductPageResponseDto;

public interface ReportProductService {
    String reportProductByID(ReportProductDto reportProductDto);

    ReportProductPageResponseDto getAllReportedProducts(Integer page, Integer count);

    ReportProductDto getReportForProductByID(Integer id);
    void deleteReportForProductByID(Integer id);

}
