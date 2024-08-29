package org.adem.supportservice.cotroller;

import org.adem.supportservice.dto.ComplaintPageResponse;
import org.adem.supportservice.dto.ComplaintRequest;
import org.adem.supportservice.dto.ComplaintResponse;
import org.adem.supportservice.service.ComplaintService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/complaint")
public class ComplaintController {
    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping("/complaint-to-us")
    @ResponseStatus(HttpStatus.CREATED)
    void complaintToUs(@RequestBody ComplaintRequest complaintRequest) {
        complaintService.complaintToUs(complaintRequest);
    }

    @GetMapping("/get-complaint-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ComplaintResponse getComplaintByID(@PathVariable Integer id) {
        return complaintService.getComplaintByID(id);
    }

    @GetMapping("/get-all-complaints")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ComplaintPageResponse getAllComplaints(@RequestParam Integer page, @RequestParam Integer count) {
        return complaintService.getAllComplaints(page,count);
    }

    @DeleteMapping("delete-complaint-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteComplaintByID(@PathVariable Integer id) {
        complaintService.deleteComplaintByID(id);
    }
}
