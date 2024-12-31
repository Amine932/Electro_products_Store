package com.example.saleservice.Repositories;

import com.example.saleservice.Entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SaleRepository extends JpaRepository<Sale,Integer> {

}
