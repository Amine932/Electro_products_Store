package com.example.productservice.Web;


import com.example.productservice.DTOs.ProductRequestDTO;
import com.example.productservice.DTOs.ProductResponseDTO;
import com.example.productservice.Service.ProductServiceInterface;
import com.example.productservice.config.GlobalConfig;
import io.micrometer.observation.annotation.Observed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController

@RequestMapping("/api")

@RefreshScope
@CrossOrigin(origins = "http://localhost:4200")

public class RestController {

    @Autowired
    ProductServiceInterface productServiceInterface;

    @Autowired
    GlobalConfig globalConfig;


    @GetMapping("/GlobalConfig")
    public GlobalConfig globalConfig()
    {
        return globalConfig;
    }

    @GetMapping("/Products")
    public List<ProductResponseDTO> listProduct()
    {
        return productServiceInterface.listProducts();
    }

    @PostMapping("/Products")
    public void addProduct(@RequestBody ProductRequestDTO productRequestDTO)
    {
        productServiceInterface.addProduct(productRequestDTO);
    }

    @PutMapping("/Products/{id}")
    public void updateProduct(@PathVariable Integer id,@RequestBody ProductRequestDTO productRequestDTO)
    {
        productServiceInterface.updateProduct(id,productRequestDTO);
    }


    @GetMapping("/Products/{id}")
    public ProductResponseDTO productById(@PathVariable Integer id)
    {
        return  productServiceInterface.productById(id);
    }

    @DeleteMapping("/Products/{id}")
    public void deleteProduct(@PathVariable Integer id)
    {
        productServiceInterface.deleteProduct(id);
    }


}
