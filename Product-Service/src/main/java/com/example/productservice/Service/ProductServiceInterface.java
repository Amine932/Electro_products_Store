package com.example.productservice.Service;


import com.example.productservice.DTOs.ProductRequestDTO;
import com.example.productservice.DTOs.ProductResponseDTO;
import com.example.productservice.Entities.Product;

import java.util.List;

public interface ProductServiceInterface {

    public List<ProductResponseDTO> listProducts ();

    public Product addProduct(ProductRequestDTO productRequestDTO);

    public ProductResponseDTO productById(Integer id);

    public void updateProduct (Integer id,ProductRequestDTO productRequestDTO);

    public void deleteProduct(Integer id);
}
