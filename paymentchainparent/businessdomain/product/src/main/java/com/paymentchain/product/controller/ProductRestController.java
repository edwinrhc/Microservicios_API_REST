package com.paymentchain.product.controller;

import com.paymentchain.product.entities.Product;
import com.paymentchain.product.respository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping()
    public List<Product> list() {
        return productRepository.findAll();
    }

    @Operation(summary = "Obtener producto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable long id, @RequestBody Product input) {
        Optional<Product> optionalproduct = productRepository.findById(id);
        if (optionalproduct.isPresent()) {
            Product newcustomer = optionalproduct.get();
            newcustomer.setName(input.getName());
            Product save = productRepository.save(newcustomer);
            return new ResponseEntity<>(save, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Product input) {
        Product save = productRepository.save(input);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
