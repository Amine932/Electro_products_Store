package com.example.clientservice.Service;

import com.example.clientservice.DTOs.ClientRequestDTO;
import com.example.clientservice.DTOs.ClientResponseDTO;
import com.example.clientservice.Entities.Client;
import com.example.clientservice.Mapper.ClientMapperInterface;
import com.example.clientservice.Repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ClientService implements ClientServiceInterface {

    @Autowired
    ClientMapperInterface clientMapperInterface;

    @Autowired
    ClientRepository clientRepository;

    @Override
    public void addClient(ClientRequestDTO clientRequestDTO) {

        Client client = clientMapperInterface.FromClientRequestDTO_2_Client(clientRequestDTO);
        clientRepository.save(client);


    }

    @Override
    public ClientResponseDTO ClientById(Integer id) {
            Client client = clientRepository.findById(id).get();

        ClientResponseDTO clientResponseDTO = clientMapperInterface.FromClient_2_ClientResponseDTO(client);
        return clientResponseDTO;
    }

    @Override
    public void Update(Integer id, ClientRequestDTO clientRequestDTO) {

        Client client1 = clientRepository.findById(id).get();
        Client client2 = clientMapperInterface.FromClientRequestDTO_2_Client(clientRequestDTO);

        client1.setName(client2.getName() != null ? client2.getName() : client1.getName());
        client1.setCity(client2.getCity() != null ? client2.getCity() : client1.getCity());
        client1.setSecondName(client2.getSecondName() != null ? client2.getSecondName() : client1.getSecondName());
        client1.setPhoneNumber(client2.getPhoneNumber() != null ? client2.getPhoneNumber() : client1.getPhoneNumber());
        client1.setNbr_sales(client2.getNbr_sales() != null ? client2.getNbr_sales() : client1.getNbr_sales());

        clientRepository.save(client1);

    }

    @Override
    public void DeleteById(Integer id) {
        clientRepository.deleteById(id);

    }

    @Override
    public List<ClientResponseDTO> listClient() {

        List<Client> clients = clientRepository.findAll();
        List<ClientResponseDTO> dtoList = new ArrayList<>();
        for (Client c : clients)
        {
            ClientResponseDTO r = clientMapperInterface.FromClient_2_ClientResponseDTO(c);
            dtoList.add(r);
        }
        return dtoList;
    }
}
