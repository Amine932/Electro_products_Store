package com.example.productservice;

import com.example.productservice.Entities.Product;
import com.example.productservice.Repositories.ProductRepository;
import com.example.productservice.config.GlobalConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableConfigurationProperties({GlobalConfig.class})

public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}



    @Bean
	CommandLineRunner start (ProductRepository productRepository)
	{
		return args -> {
			Product smartphone = Product.builder()
					.brand("Smartphone Samsung")
					.desc("Smartphone haut de gamme avec écran AMOLED de 6,8 pouces...")
					.price(1299.99)
					.quantity(50)
					.build();

			Product laptop = Product.builder()
					.brand("Laptop Dell")
					.desc("Ordinateur portable puissant avec écran tactile 4K de 15,6 pouces...")
					.price(1799.00)
					.quantity(35)
					.build();

			Product headphones = Product.builder()
					.brand("Headphones Sony")
					.desc("Écouteurs Bluetooth à réduction de bruit avec une autonomie de batterie de 30 heures...")
					.price(299.99)
					.quantity(20)
					.build();

			Product camera = Product.builder()
					.brand(" Camera Canon")
					.desc("Caméra sans miroir haut de gamme avec capteur CMOS plein format de 45 MP...")
					.price(3899.00)
					.quantity(15)
					.build();

			Product smartwatch = Product.builder()
					.brand("Smartwatch Apple")
					.desc("Montre intelligente avec écran OLED Retina, suivi avancé de la condition physique...")
					.price(399.00)
					.quantity(45)
					.build();

			Product tv = Product.builder()
					.brand("Tv LG")
					.desc("Télévision OLED 4K avec processeur α9 Gen4 AI, Dolby Vision IQ...")
					.price(2499.00)
					.quantity(19)
					.build();

			Product gameConsole = Product.builder()
					.brand("Game Console Sony")
					.desc("Console de jeu avec processeur AMD Zen 2, graphiques Radeon RDNA 2...")
					.price(499.99)
					.quantity(30)
					.build();

			Product smartSpeaker = Product.builder()
					.brand("Smart Speaker Amazon")
					.desc("Enceinte connectée avec assistant vocal Alexa intégré, qualité audio améliorée...")
					.price(99.99)
					.quantity(11)
					.build();

			Product camera2 = Product.builder()
					.brand("Camera Sony")
					.desc("Appareil photo hybride avec capteur CMOS APS-C de 26,1 MP, stabilisation d'image...")
					.price(1699.00)
					.quantity(18)
					.build();

			Product vrHeadset = Product.builder()
					.brand("Vr Headset Oculus")
					.desc("Casque VR autonome avec résolution de 1832x1920 pixels par œil, 6 Go de RAM...")
					.price(299.00)
					.quantity(22)
					.build();
			Product jblTune510BT = Product.builder()
					.brand("JBL TUNE 510BT")
					.desc("Wireless Bluetooth Headphones")
					.price(99.99)
					.quantity(50)
					.build();

			Product iPhone15 = Product.builder()
					.brand("iPhone 15")
					.desc("Smartphone")
					.price(1299.99)
					.quantity(19)
					.build();

			Product xiaomiBook14 = Product.builder()
					.brand("Xiaomi Book 14")
					.desc("Laptop")
					.price(899.99)
					.quantity(17)
					.build();

			productRepository.save(camera);
			productRepository.save(smartwatch);
			productRepository.save(headphones);
			productRepository.save(laptop);
			productRepository.save(gameConsole);
			productRepository.save(smartSpeaker);
			productRepository.save(camera2);
			productRepository.save(tv);
			productRepository.save(vrHeadset);
			productRepository.save(smartphone);
			productRepository.save(jblTune510BT);
			productRepository.save(iPhone15);
			productRepository.save(xiaomiBook14);


		};
	}
}
