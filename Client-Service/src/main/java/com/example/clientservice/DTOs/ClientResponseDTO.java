package com.example.clientservice.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class ClientResponseDTO {

    private Integer id;
    private String name;
    private String city;
    private String secondName;
    private String phoneNumber;
    private Integer nbr_sales;
    private String photoUrl;


}
