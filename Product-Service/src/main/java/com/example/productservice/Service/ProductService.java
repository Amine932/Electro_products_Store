package com.example.productservice.Service;

import com.example.productservice.DTOs.ProductRequestDTO;
import com.example.productservice.DTOs.ProductResponseDTO;
import com.example.productservice.Entities.Product;
import com.example.productservice.Mapper.ProductMapperInterface;
import com.example.productservice.Repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional

public class ProductService implements ProductServiceInterface {

    @Autowired
    ProductMapperInterface productMapperInterface;


    @Autowired
    ProductRepository productRepository;


    @Override
    public List<ProductResponseDTO> listProducts() {

        List<Product> products = productRepository.findAll();
        List<ProductResponseDTO> dtoList = new ArrayList<>();
        for (Product p : products)
        {
            ProductResponseDTO r = productMapperInterface.FromProduct_2_ProductResponseDTO(p);
            dtoList.add(r);
        }
        return dtoList;
    }


    @Override
    public Product addProduct(ProductRequestDTO productRequestDTO) {

        Product product = productMapperInterface.FromProductRequestDTO_2_Product(productRequestDTO);

         return productRepository.save(product);


    }

    @Override
    public ProductResponseDTO productById(Integer id) {
        Product product = productRepository.findById(id).get();

        ProductResponseDTO dto = productMapperInterface.FromProduct_2_ProductResponseDTO(product);

        return dto;

    }

    @Override
    public void updateProduct(Integer id, ProductRequestDTO productRequestDTO) {

        Product product1 = productRepository.findById(id).get();
        Product product2 =productMapperInterface.FromProductRequestDTO_2_Product(productRequestDTO);

        product1.setBrand(product2.getBrand()!=null? product2.getBrand() : product1.getBrand());
        product1.setPrice(product2.getPrice()!=null? product2.getPrice() : product1.getPrice());
        product1.setDesc(product2.getDesc()!=null? product2.getDesc() : product1.getDesc());
        product1.setQuantity(product2.getQuantity()!=null? product2.getQuantity() : product1.getQuantity());

        productRepository.save(product1);

    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);

    }
}
