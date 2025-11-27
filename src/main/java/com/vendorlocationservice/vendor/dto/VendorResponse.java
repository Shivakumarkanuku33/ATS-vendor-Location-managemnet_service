package com.vendorlocationservice.vendor.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VendorResponse {
    private Long id;
    private String vendorName;
    private String contactPerson;
    private String phone;
    private String email;
    private String status;
}
