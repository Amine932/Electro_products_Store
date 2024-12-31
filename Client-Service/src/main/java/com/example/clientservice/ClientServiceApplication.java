package com.example.clientservice;

import com.example.clientservice.Config.ClientConfig;
import com.example.clientservice.Entities.Client;
import com.example.clientservice.Repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties({ClientConfig.class})
@EnableDiscoveryClient
public class ClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ClientRepository clientRepository)
	{
		return args ->
		{

			Client client1 = Client.builder()
					.name("Karim")
					.secondName("Ben Ahmed")
					.city("Casablanca")
					.phoneNumber("0612345678")
					.build();

			Client client2 = Client.builder()
					.name("Lina")
					.secondName("El Mansouri")
					.city("Marrakech")
					.phoneNumber("0623456789")
					.nbr_sales(0)
					.build();

			Client client3 = Client.builder()
					.name("Youssef")
					.secondName("El Amrani")
					.city("Rabat")
					.phoneNumber("0634567890")
					.nbr_sales(8)
					.build();

			Client client4 = Client.builder()
					.name("Fatima")
					.secondName("Zahraoui")
					.city("Tanger")
					.phoneNumber("0645678901")
					.nbr_sales(4)
					.build();

			Client client5 = Client.builder()
					.name("Ahmed")
					.secondName("Bouazza")
					.city("Fès")
					.phoneNumber("0656789012")
					.nbr_sales(0)
					.build();

			Client client6 = Client.builder()
					.name("Nora")
					.secondName("Ait Hamou")
					.city("Oujda")
					.phoneNumber("0667890123")
					.nbr_sales(3)
					.build();

			Client client7 = Client.builder()
					.name("Omar")
					.secondName("Cherkaoui")
					.city("Meknès")
					.phoneNumber("0678901234")
					.nbr_sales(2)
					.build();

			Client client8 = Client.builder()
					.name("Sara")
					.secondName("El Houari")
					.city("El Jadida")
					.phoneNumber("0689012345")
					.nbr_sales(0)
					.build();

			clientRepository.save(client1);
			clientRepository.save(client2);
			clientRepository.save(client3);
			clientRepository.save(client4);
			clientRepository.save(client5);
			clientRepository.save(client6);
			clientRepository.save(client7);
			clientRepository.save(client8);


		};

	}
}
