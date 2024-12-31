package com.example.clientservice.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ClientRequestDTO {
    private String name;
    private String city;
    private String secondName;
    private String phoneNumber;
    private Integer nbr_sales;
    private String photoUrl;


}
