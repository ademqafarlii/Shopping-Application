package org.adem.supportservice.service.impl;

import org.adem.supportservice.dto.ComplaintPageResponse;
import org.adem.supportservice.dto.ComplaintRequest;
import org.adem.supportservice.dto.ComplaintResponse;
import org.adem.supportservice.exception.ComplaintNotFoundException;
import org.adem.supportservice.mapper.ComplaintMapper;
import org.adem.supportservice.model.Complaint;
import org.adem.supportservice.repository.ComplaintRepository;
import org.adem.supportservice.service.ComplaintService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ComplaintServiceImpl implements ComplaintService {
    private final ComplaintRepository complaintRepository;
    private final ComplaintMapper complaintMapper;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository, ComplaintMapper complaintMapper) {
        this.complaintRepository = complaintRepository;
        this.complaintMapper = complaintMapper;
    }


    @Override
    public void complaintToUs(ComplaintRequest complaintRequest) {
        complaintRepository.save(complaintMapper.toComplaint(complaintRequest));
    }

    @Override
    public ComplaintResponse getComplaintByID(Integer id) {
        return complaintRepository.findById(id)
                .stream()
                .map(complaintMapper::toComplaintRequest)
                .findFirst()
                .orElseThrow(()->new ComplaintNotFoundException("Complaint not found"));
    }


    @Override
    public ComplaintPageResponse getAllComplaints(Integer page, Integer count) {
        Page<Complaint> complaints = complaintRepository.findAll(PageRequest.of(page,count));
        if (complaints.isEmpty()){
            throw new ComplaintNotFoundException("Complaint not found");
        }
        return new ComplaintPageResponse(
                complaints.getContent().stream().map(complaintMapper::toComplaintRequest).collect(Collectors.toList()),
                complaints.getTotalPages(),
                complaints.getTotalElements(),
                complaints.hasNext()
        );
    }

    @Override
    public void deleteComplaintByID(Integer id) {
        complaintRepository.deleteById(id);
    }

}
