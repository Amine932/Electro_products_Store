package com.example.productservice.Mapper;

import com.example.productservice.DTOs.ProductRequestDTO;
import com.example.productservice.DTOs.ProductResponseDTO;
import com.example.productservice.Entities.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements ProductMapperInterface{
    @Override
    public Product FromProductRequestDTO_2_Product(ProductRequestDTO productRequestDTO) {

        Product product = new Product();

        BeanUtils.copyProperties(productRequestDTO,product);

        return product;
    }

    @Override
    public ProductResponseDTO FromProduct_2_ProductResponseDTO(Product product) {

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        BeanUtils.copyProperties(product,productResponseDTO);

        return productResponseDTO;
    }
}
