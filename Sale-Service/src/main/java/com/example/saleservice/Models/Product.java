package com.example.saleservice.Models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Product {


    private Integer id;

    private String brand;

    private String desc;

    private Double price;

    private Integer quantity;
}
