package com.example.saleservice.DTOs;


import com.example.saleservice.Models.Client;
import com.example.saleservice.Models.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleRequestDTO {



    private String year;

    private String month;

    private Integer productQuantity;


    private Integer idProduct;

    private Integer idClient;

}
