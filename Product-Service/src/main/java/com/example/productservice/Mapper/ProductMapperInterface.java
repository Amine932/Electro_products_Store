package com.example.productservice.Mapper;

import com.example.productservice.DTOs.ProductRequestDTO;
import com.example.productservice.DTOs.ProductResponseDTO;
import com.example.productservice.Entities.Product;

public interface ProductMapperInterface {
    public Product FromProductRequestDTO_2_Product(ProductRequestDTO productRequestDTO);

    public ProductResponseDTO FromProduct_2_ProductResponseDTO(Product product);
}
