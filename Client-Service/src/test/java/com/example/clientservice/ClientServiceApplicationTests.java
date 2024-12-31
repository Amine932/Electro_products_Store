package com.example.clientservice;

import com.example.clientservice.Repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ClientServiceApplicationTest {

	@Autowired
	private ClientRepository clientRepository;

	@Test
	void testStart() throws Exception {

		new ClientServiceApplication().start(clientRepository).run();


		assertEquals(8, clientRepository.count(), "Le nombre de clients dans la base de données est incorrect");
	}

	@TestConfiguration
	static class TestConfig {
		@Bean
		CommandLineRunner start(ClientRepository clientRepository) {
			return args -> {
			};
		}
	}
}
