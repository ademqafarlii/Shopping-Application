package org.adem.reportservice.service;

import org.adem.reportservice.dto.ReportCustomerPageResponse;
import org.adem.reportservice.dto.ReportCustomerDto;
import org.adem.reportservice.dto.ReportProductDto;
import org.adem.reportservice.dto.ReportProductPageResponseDto;

public interface ReportCustomerService {

    String reportCustomerByName(ReportCustomerDto reportCustomerDto);

    ReportCustomerDto getReportForCustomerByID(Integer id);

    ReportCustomerPageResponse getAllReportedCustomers(Integer page, Integer count);
    void deleteReportForCustomerByID(Integer id);

}
