package org.adem.supportservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComplaintPageResponse {
    private List<ComplaintResponse> complaintList;
    private long totalPages;
    private long totalElements;
    private boolean hasNext;
}
