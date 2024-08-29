package org.adem.supportservice.service;

import org.adem.supportservice.dto.ComplaintPageResponse;
import org.adem.supportservice.dto.ComplaintRequest;
import org.adem.supportservice.dto.ComplaintResponse;

public interface ComplaintService {

    void complaintToUs(ComplaintRequest complaintRequest);

    ComplaintResponse getComplaintByID(Integer id);

    ComplaintPageResponse getAllComplaints(Integer page, Integer count);

    void deleteComplaintByID(Integer id);

}
