package com.example.FakeCommerce.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.FakeCommerce.dtos.CreateProductRequestDto;
import com.example.FakeCommerce.dtos.GetProductResponseDto;
import com.example.FakeCommerce.dtos.GetProductWithDetailedResponseDto;
import com.example.FakeCommerce.schema.Product;
import java.util.*;

import com.example.FakeCommerce.services.ProductService;

import lombok.AllArgsConstructor;

@RequestMapping("api/v1/Products")
@RestController
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<GetProductResponseDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody CreateProductRequestDto requestDto){
        return productService.createProduct(requestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @GetMapping("/search")
    public List<Product> getProductByCategory(@RequestParam("categoryId") Long categoryId){
        return productService.getProductByCategory(categoryId);
    }

    @GetMapping("/{id}/details")
    public GetProductWithDetailedResponseDto getProductWithDetailsById(@PathVariable Long id){
        return productService.getProductWithDetailsById(id);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.geProductById(id);
    }

    //get all unique categories
    @GetMapping("/categories")
    public List<String> getAllUniqueCategories(){
        return productService.getAllUniqueCategories();
    }
}
