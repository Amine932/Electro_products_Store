package com.example.saleservice.Service;

import com.example.saleservice.Clients.ClientFeignController;
import com.example.saleservice.DTOs.SaleRequestDTO;
import com.example.saleservice.DTOs.SaleResponseDTO;
import com.example.saleservice.Entities.Sale;
import com.example.saleservice.Mapper.SaleMapperInterface;
import com.example.saleservice.Models.Client;
import com.example.saleservice.Models.Product;
import com.example.saleservice.Products.ProductFeignController;
import com.example.saleservice.Repositories.SaleRepository;
import com.example.saleservice.event.SaleEvent;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SaleService implements SaleServiceInterface {

    @Autowired
    SaleMapperInterface saleMapperInterface;

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    ProductFeignController productFeigncontroller;

    @Autowired
    ClientFeignController clientFeignController;

    @Autowired
    KafkaTemplate<String, SaleEvent> template;

    @Override
    public void addSale(SaleRequestDTO saleRequestDTO) {

        Sale sale = saleMapperInterface.FromSaleRequestDTO_2_Sale(saleRequestDTO);
        saleRepository.save(sale);
        template.send("notificationTopic", new SaleEvent(sale.getId(),sale.getIdClient(), sale.getIdProduct()));



    }


    @Override
    public SaleResponseDTO SaleById(Integer id) {
        Product product;
        Client client;
        Sale sale = saleRepository.findById(id).get();
        Integer idProduct = sale.getIdProduct();
        Integer idClient = sale.getIdClient();

        if (idProduct != null) {
            product = productFeigncontroller.findProductById(idProduct);
        } else {
            product = null;
        }
        sale.setProduct(product);

        if (idClient != null) {
            client = clientFeignController.findClientById(idClient);
        } else {
            client = null;
        }

        sale.setProduct(product);
        sale.setClient(client);

        SaleResponseDTO saleResponseDTO = saleMapperInterface.FromSale_2_SaleResponseDTO(sale);
        return saleResponseDTO;
    }


    @Override
    public void Update(Integer id, SaleRequestDTO saleRequestDTO) {

        Sale sale1 = saleRepository.findById(id).get();
        Sale sale2 = saleMapperInterface.FromSaleRequestDTO_2_Sale(saleRequestDTO);

        sale1.setIdClient(sale2.getIdClient() != null ? sale2.getIdClient() : sale1.getIdClient());
        sale1.setIdProduct(sale2.getIdProduct() != null ? sale2.getIdProduct() : sale1.getIdProduct());
        sale1.setMonth(sale2.getMonth() != null ? sale2.getMonth() : sale1.getMonth());
        sale1.setYear(sale2.getYear() != null ? sale2.getYear() : sale1.getYear());
        sale1.setProductQuantity(sale2.getProductQuantity() != null ? sale2.getProductQuantity() : sale1.getProductQuantity());


        saleRepository.save(sale1);

    }

    @Override
    public void DeleteById(Integer id) {
        saleRepository.deleteById(id);

    }

    @Override
    public List<SaleResponseDTO> listSale() {
        List<Sale> saleList = saleRepository.findAll();

        Product product;
        Client client;


        List<SaleResponseDTO> listR = new ArrayList<>();
        SaleResponseDTO saleResponseDTO;

        for (Sale sale : saleList) {

            Integer idProduct = sale.getIdProduct();
            Integer idClient = sale.getIdClient();


            if (idProduct != null) {
                product = productFeigncontroller.findProductById(idProduct);
            } else {
                product = null;
            }

            if (idClient != null) {
                client = clientFeignController.findClientById(idClient);
            } else {
                client = null;
            }

            sale.setProduct(product);
            sale.setClient(client);


            saleResponseDTO = saleMapperInterface.FromSale_2_SaleResponseDTO(sale);
            listR.add(saleResponseDTO);

        }
        return listR;
    }
}
