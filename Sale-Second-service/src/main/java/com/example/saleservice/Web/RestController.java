package com.example.saleservice.Web;

import com.example.saleservice.DTOs.SaleRequestDTO;
import com.example.saleservice.DTOs.SaleResponseDTO;
import com.example.saleservice.Service.SaleServiceInterface;
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
    SaleServiceInterface saleServiceInterface;


    @PostMapping("/Sales")
    public void addSale(@RequestBody SaleRequestDTO saleRequestDTO)
    {
        saleServiceInterface.addSale(saleRequestDTO);
    }
    @GetMapping("/Sales")
    public List<SaleResponseDTO> getAll()
    {
        return saleServiceInterface.listSale();
    }


    @GetMapping("/Sales/{id}")
    public SaleResponseDTO getById(@PathVariable Integer id)
    {
        return saleServiceInterface.SaleById(id);

    }

    @PutMapping("/Sales/{id}")
    public void Update(@PathVariable Integer id,@RequestBody SaleRequestDTO saleRequestDTO){
        saleServiceInterface.Update(id,saleRequestDTO);
    }

    @DeleteMapping("/Sales/{id}")
    public void Delete(@PathVariable Integer id)
    {
        saleServiceInterface.DeleteById(id);

    }



}
