package com.example.saleservice.Mapper;

import com.example.saleservice.DTOs.SaleRequestDTO;
import com.example.saleservice.DTOs.SaleResponseDTO;
import com.example.saleservice.Entities.Sale;

public interface SaleMapperInterface {

    public Sale FromSaleRequestDTO_2_Sale (SaleRequestDTO clientRequestDTO);
    public SaleResponseDTO FromSale_2_SaleResponseDTO(Sale client);
}
