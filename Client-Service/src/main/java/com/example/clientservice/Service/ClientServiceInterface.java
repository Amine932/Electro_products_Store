package com.example.clientservice.Service;

import com.example.clientservice.DTOs.ClientRequestDTO;
import com.example.clientservice.DTOs.ClientResponseDTO;
import com.example.clientservice.Entities.Client;

import java.util.List;

public interface ClientServiceInterface {

    public void addClient(ClientRequestDTO clientRequestDTO);

    public List<ClientResponseDTO> listClient();

    public ClientResponseDTO ClientById(Integer id);

    public void Update (Integer id , ClientRequestDTO clientRequestDTO);
    public void DeleteById (Integer id);
}
