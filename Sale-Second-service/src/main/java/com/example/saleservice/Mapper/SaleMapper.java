package com.example.saleservice.Mapper;


import com.example.saleservice.DTOs.SaleRequestDTO;
import com.example.saleservice.DTOs.SaleResponseDTO;
import com.example.saleservice.Entities.Sale;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SaleMapper implements SaleMapperInterface {
    @Override
    public Sale FromSaleRequestDTO_2_Sale(SaleRequestDTO saleRequestDTO) {
        Sale sale =  new Sale();
        BeanUtils.copyProperties(saleRequestDTO,sale);

        return sale;
    }

    @Override
    public SaleResponseDTO FromSale_2_SaleResponseDTO(Sale sale) {
        SaleResponseDTO saleResponseDTO = new SaleResponseDTO();
        BeanUtils.copyProperties(sale,saleResponseDTO);


        return saleResponseDTO;
    }


}
