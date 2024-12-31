package com.example.saleservice;

import com.example.saleservice.Entities.Sale;
import com.example.saleservice.Repositories.SaleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
@EnableKafka
public class SaleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaleServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(SaleRepository saleRepository)
    {
        return args ->
        {
            Sale sale1 =   Sale.builder().month("Janvier").year("2023").productQuantity(7).idProduct(1).idClient(1).build();
            Sale sale2 =   Sale.builder().month("Février").year("2023").productQuantity(6).idProduct(2).idClient(1).build();
            Sale sale3 =   Sale.builder().month("Mars").year("2023").productQuantity(3).idProduct(3).idClient(1).build();
            Sale sale4 =   Sale.builder().month("Avril").year("2023").productQuantity(1).idProduct(4).idClient(7).build();
            Sale sale5 =   Sale.builder().month("Mai").year("2023").productQuantity(2).idProduct(1).idClient(2).build();
            Sale sale6 =   Sale.builder().month("Janvier").year("2015").productQuantity(5).idProduct(1).idClient(3).build();
            Sale sale7 =   Sale.builder().month("Février").year("2022").productQuantity(3).idProduct(10).idClient(7).build();
            Sale sale8 =   Sale.builder().month("Mars").year("2022").productQuantity(6).idProduct(2).idClient(5).build();
            Sale sale9 =   Sale.builder().month("Avril").year("2019").productQuantity(7).idProduct(4).idClient(5).build();
            Sale sale10 =  Sale.builder().month("Mai").year("2020").productQuantity(9).idProduct(5).idClient(8).build();
            saleRepository.save(sale1);
            saleRepository.save(sale2);
            saleRepository.save(sale3);
            saleRepository.save(sale4);
            saleRepository.save(sale5);
            saleRepository.save(sale6);
            saleRepository.save(sale7);
            saleRepository.save(sale8);
            saleRepository.save(sale9);
            saleRepository.save(sale10);
        };

    }



}
