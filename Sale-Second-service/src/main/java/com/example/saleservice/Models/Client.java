package com.example.saleservice.Models;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Client {

    private Integer id;
    private String name;
    private String city;
    private String secondName;
    private String phoneNumber;



}
