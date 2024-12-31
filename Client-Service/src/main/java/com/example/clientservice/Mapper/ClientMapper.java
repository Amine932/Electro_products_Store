package com.example.clientservice.Mapper;

import com.example.clientservice.DTOs.ClientRequestDTO;
import com.example.clientservice.DTOs.ClientResponseDTO;
import com.example.clientservice.Entities.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper implements ClientMapperInterface{
    @Override
    public Client FromClientRequestDTO_2_Client(ClientRequestDTO clientRequestDTO) {
        Client client =  new Client();
        BeanUtils.copyProperties(clientRequestDTO,client);

        return client;
    }

    @Override
    public ClientResponseDTO FromClient_2_ClientResponseDTO(Client client) {
        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
        BeanUtils.copyProperties(client,clientResponseDTO);


        return clientResponseDTO;
    }
}
