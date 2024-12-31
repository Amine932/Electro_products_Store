package com.example.saleservice.Products;

import com.example.saleservice.Models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductFeignController
{

    @GetMapping("/api/Products")
    public List<Product> findAllProduct();


    @GetMapping("/api/Products/{id}")
    public Product findProductById(@PathVariable Integer id);

}
