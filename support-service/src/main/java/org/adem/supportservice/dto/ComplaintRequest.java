package org.adem.supportservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ComplaintRequest {
    private Integer id;

    private String customerName;
    private String email;
    private String tittle;
    private String complaint;
    private String password;
    private String urlOfConfirmingTheComplaint;
}
