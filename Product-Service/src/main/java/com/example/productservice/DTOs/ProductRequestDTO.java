package com.example.productservice.DTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductRequestDTO {
    private String brand;
    private String desc;
    private Double price;
    private Integer quantity;

}
