package com.vendorlocationservice.location.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LocationRequest {
    private String name;
    private String address;
    private Long parentId; // For hierarchy mapping
}
