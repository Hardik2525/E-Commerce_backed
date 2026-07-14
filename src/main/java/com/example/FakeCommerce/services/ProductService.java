package com.example.FakeCommerce.services;

import org.springframework.stereotype.Service;

import com.example.FakeCommerce.dtos.CreateProductRequestDto;
import com.example.FakeCommerce.repositories.ProductRepository;
import com.example.FakeCommerce.schema.Product;

import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product geProductById(Long id){
        return productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Prodcut not found"));
    }

    public Product createProduct(CreateProductRequestDto requestDto){

        Product newProduct = Product.builder()
        .title(requestDto.getTitle())
        .description(requestDto.getDescription())
        .price(requestDto.getPrice())
        .image(requestDto.getImage())
        .category(requestDto.getCategory())
        .rating(requestDto.getRating())
        .build();

        return productRepository.save(newProduct);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> getProductByCategory(String category){
        return productRepository.findByCategory(category);
    } 
    
}
