package com.paymentchain.product.controller;

import com.paymentchain.product.entities.Product;
import com.paymentchain.product.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public List<Product> list(){
        return productRepository.findAll();
    }

    //TODO: Agregar Enpoints

}
