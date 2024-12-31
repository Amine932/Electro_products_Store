package com.example.saleservice.Entities;

import com.example.saleservice.Models.Client;
import com.example.saleservice.Models.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Sale {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "\"year\"")
    private String year;

    @Column(name = "\"month\"")
    private String month;

    private Integer idProduct;

    private Integer idClient;

    private Integer productQuantity;

    @Transient
    private Product product;

    @Transient
    private Client client;


}
