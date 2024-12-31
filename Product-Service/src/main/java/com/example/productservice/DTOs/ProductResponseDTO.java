package com.example.productservice.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class ProductResponseDTO {
    private Integer id;
    private String brand;
    private String desc;
    private Double price;
    private Integer quantity;
}
