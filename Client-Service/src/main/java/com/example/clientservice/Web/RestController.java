package com.example.clientservice.Web;

import com.example.clientservice.Config.ClientConfig;
import com.example.clientservice.DTOs.ClientRequestDTO;
import com.example.clientservice.DTOs.ClientResponseDTO;
import com.example.clientservice.Service.ClientServiceInterface;
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
    ClientServiceInterface clientServiceInterface;


    @Autowired
    ClientConfig clientConfig;


    @GetMapping("/GlobalConfig")
    public ClientConfig globalConfig()
    {
        return clientConfig;
    }


    @PostMapping("/Clients")
    public void addClient(@RequestBody ClientRequestDTO clientRequestDTO)
    {
        clientServiceInterface.addClient(clientRequestDTO);
    }
    @GetMapping("/Clients")
    public List<ClientResponseDTO> getAll()
    {
        return clientServiceInterface.listClient();
    }


    @GetMapping("/Clients/{id}")
    public ClientResponseDTO getById(@PathVariable Integer id)
    {
        return clientServiceInterface.ClientById(id);

    }

    @PutMapping("/Clients/{id}")
    public void Update(@PathVariable Integer id,@RequestBody ClientRequestDTO clientRequestDTO){
        clientServiceInterface.Update(id,clientRequestDTO);
    }

    @DeleteMapping("/Clients/{id}")
    public void Delete(@PathVariable Integer id)
    {
        clientServiceInterface.DeleteById(id);

    }



}
