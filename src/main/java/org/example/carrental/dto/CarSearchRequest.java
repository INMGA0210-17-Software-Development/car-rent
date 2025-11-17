package org.example.carrental.dto;

import lombok.Data;

@Data
public class CarSearchRequest {
    private String location;
    private String brand;
    private String sortBy;
}
