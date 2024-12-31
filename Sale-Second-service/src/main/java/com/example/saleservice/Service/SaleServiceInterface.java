package com.example.saleservice.Service;

import com.example.saleservice.DTOs.SaleRequestDTO;
import com.example.saleservice.DTOs.SaleResponseDTO;

import java.util.List;

public interface SaleServiceInterface {


    public void addSale(SaleRequestDTO saleRequestDTO);

    public List<SaleResponseDTO> listSale();

    public SaleResponseDTO SaleById(Integer id);

    public void Update (Integer id , SaleRequestDTO saleRequestDTO);
    public void DeleteById (Integer id);
}
