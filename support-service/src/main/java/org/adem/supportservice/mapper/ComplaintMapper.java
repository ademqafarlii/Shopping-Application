package org.adem.supportservice.mapper;

import org.adem.supportservice.dto.ComplaintRequest;
import org.adem.supportservice.dto.ComplaintResponse;
import org.adem.supportservice.exception.ComplaintNotFoundException;
import org.adem.supportservice.model.Complaint;
import org.springframework.stereotype.Service;

@Service
public class ComplaintMapper {

    public Complaint toComplaint(ComplaintRequest complaintRequest){
        if (complaintRequest==null){
            throw new ComplaintNotFoundException("Complaint not found");
        }
        return Complaint.builder()
                .email(complaintRequest.getEmail())
                .customerName(complaintRequest.getCustomerName())
                .complaint(complaintRequest.getComplaint())
                .tittle(complaintRequest.getTittle())
                .password(complaintRequest.getPassword())
                .urlOfConfirmingTheComplaint(complaintRequest.getUrlOfConfirmingTheComplaint())
                .build();
    }

    public ComplaintResponse toComplaintRequest(Complaint complaint){
        if (complaint==null){
            throw new ComplaintNotFoundException("Complaint not found");
        }
        return ComplaintResponse.builder()
                .email(complaint.getEmail())
                .customerName(complaint.getCustomerName())
                .complaint(complaint.getComplaint())
                .tittle(complaint.getTittle())
                .password(complaint.getPassword())
                .urlOfConfirmingTheComplaint(complaint.getUrlOfConfirmingTheComplaint())
                .build();
    }


}
