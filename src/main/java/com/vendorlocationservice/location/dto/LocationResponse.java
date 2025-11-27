package com.vendorlocationservice.location.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class LocationResponse {
    private Long id;
    private String name;
    private String address;
    private String status;
    private Long parentId;
}
