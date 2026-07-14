package com.example.FakeCommerce.services;

import org.springframework.stereotype.Service;

import com.example.FakeCommerce.repositories.ProductRepository;
import com.example.FakeCommerce.schema.Product;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product geProductById(Long id){
        return productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Prodcut not found"));
    }
    
}
