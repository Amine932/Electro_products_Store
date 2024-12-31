package com.example.clientservice.Service;

import com.example.clientservice.DTOs.ClientRequestDTO;
import com.example.clientservice.DTOs.ClientResponseDTO;
import com.example.clientservice.Entities.Client;
import com.example.clientservice.Mapper.ClientMapperInterface;
import com.example.clientservice.Repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @Mock
    private ClientMapperInterface clientMapperInterface;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddClient() {
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO();
        Client client = new Client();

        when(clientMapperInterface.FromClientRequestDTO_2_Client(any())).thenReturn(client);

        clientService.addClient(clientRequestDTO);

        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testClientById() {
        Client client = new Client();
        when(clientRepository.findById(anyInt())).thenReturn(Optional.of(client));
        when(clientMapperInterface.FromClient_2_ClientResponseDTO(any())).thenReturn(new ClientResponseDTO());

        ClientResponseDTO result = clientService.ClientById(1);

        assertEquals(ClientResponseDTO.class, result.getClass());
    }

    @Test
    void testUpdate() {
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO();
        Client client1 = new Client();
        Client client2 = new Client();

        when(clientRepository.findById(anyInt())).thenReturn(Optional.of(client1));
        when(clientMapperInterface.FromClientRequestDTO_2_Client(any())).thenReturn(client2);

        clientService.Update(1, clientRequestDTO);

        verify(clientRepository, times(1)).save(client1);
    }

    @Test
    void testDeleteById() {
        clientService.DeleteById(1);

        verify(clientRepository, times(1)).deleteById(1);
    }

    @Test
    void testListClient() {
        List<Client> clients = Arrays.asList(new Client(), new Client());
        when(clientRepository.findAll()).thenReturn(clients);
        when(clientMapperInterface.FromClient_2_ClientResponseDTO(any())).thenReturn(new ClientResponseDTO());

        List<ClientResponseDTO> result = clientService.listClient();

        assertEquals(2, result.size());
    }
}
