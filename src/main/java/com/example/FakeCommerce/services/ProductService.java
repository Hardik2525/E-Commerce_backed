package com.example.FakeCommerce.services;

import org.springframework.stereotype.Service;

import com.example.FakeCommerce.dtos.CreateProductRequestDto;
import com.example.FakeCommerce.dtos.GetProductResponseDto;
import com.example.FakeCommerce.dtos.GetProductWithDetailedResponseDto;
import com.example.FakeCommerce.repositories.CategoryRepository;
import com.example.FakeCommerce.repositories.ProductRepository;
import com.example.FakeCommerce.schema.Category;
import com.example.FakeCommerce.schema.Product;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<GetProductResponseDto> getAllProducts() {
        List<Product> Products =  productRepository.findAll();
        List<GetProductResponseDto> responses = new ArrayList<>();
        for(Product product : Products){
            GetProductResponseDto response = GetProductResponseDto.builder()
            .id(product.getId())
            .title(product.getTitle())
            .description(product.getDescription())
            .price(product.getPrice())
            .image(product.getImage())
            .rating(product.getRating())
            .build();
            responses.add(response);
        }
        return responses;
    }

    public Product geProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public GetProductWithDetailedResponseDto getProductWithDetailsById(Long id) {
        Product product = productRepository.findProductWithDetailsById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return GetProductWithDetailedResponseDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImage())
                .rating(product.getRating())
                .category(product.getCategory().getName())
                .build();
    }

    public Product createProduct(CreateProductRequestDto requestDto) {
        Category newCategory = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product newProduct = Product.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .price(requestDto.getPrice())
                .image(requestDto.getImage())
                .category(newCategory)
                .rating(requestDto.getRating())
                .build();

        return productRepository.save(newProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public List<String> getAllUniqueCategories() {
        return productRepository.getAllUniqueCategories();
    }
}
