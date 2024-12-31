package com.example.clientservice.Mapper;

import com.example.clientservice.DTOs.ClientRequestDTO;
import com.example.clientservice.DTOs.ClientResponseDTO;
import com.example.clientservice.Entities.Client;

public interface ClientMapperInterface {
    public Client FromClientRequestDTO_2_Client (ClientRequestDTO clientRequestDTO);
    public ClientResponseDTO FromClient_2_ClientResponseDTO(Client client);
}
