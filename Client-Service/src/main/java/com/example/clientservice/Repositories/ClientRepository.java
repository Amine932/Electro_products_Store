package com.example.clientservice.Repositories;

import com.example.clientservice.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ClientRepository extends JpaRepository<Client,Integer> {

}
