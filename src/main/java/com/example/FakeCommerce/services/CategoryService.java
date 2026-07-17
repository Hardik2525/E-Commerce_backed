package com.example.FakeCommerce.services;

import org.springframework.stereotype.Service;

import com.example.FakeCommerce.dtos.CreateCategoryRequestDto;
import com.example.FakeCommerce.exceptions.ResourceNotFoundException;
import com.example.FakeCommerce.repositories.CategoryRepository;
import com.example.FakeCommerce.schema.Category;
import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category createCategory(CreateCategoryRequestDto createCategory) {
        Category category = Category.builder()
                .name(createCategory.getName())
                .build();
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id)
        .orElseThrow(()->new ResourceNotFoundException("Category not found"));
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
