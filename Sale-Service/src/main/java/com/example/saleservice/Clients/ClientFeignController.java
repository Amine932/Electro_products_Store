package com.example.saleservice.Clients;

import com.example.saleservice.Models.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CLIENT-SERVICE")
public interface ClientFeignController
{

    @GetMapping("/api/Clients")
    public List<Client> findAllClient();


    @GetMapping("/api/Clients/{id}")
    public Client findClientById(@PathVariable Integer id);

}
