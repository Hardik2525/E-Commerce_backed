package com.example.FakeCommerce.services;

import org.springframework.stereotype.Service;

import com.example.FakeCommerce.adapters.OrderAdapter;
import com.example.FakeCommerce.dtos.CreateCategoryRequestDto;
import com.example.FakeCommerce.dtos.GetCategoryResponseDto;
import com.example.FakeCommerce.exceptions.ResourceNotFoundException;
import com.example.FakeCommerce.repositories.CategoryRepository;
import com.example.FakeCommerce.schema.Category;
import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final OrderAdapter orderAdapter;

    public Category createCategory(CreateCategoryRequestDto createCategory) {
        Category category = Category.builder()
                .name(createCategory.getName())
                .build();
        return categoryRepository.save(category);
    }

    public List<GetCategoryResponseDto> getAllCategories(){
        List<Category> categories =  categoryRepository.findAll();
        // List<category> to List<GetCategoryResponseDto>
        return orderAdapter.mapToCategoryResponseDtoList(categories);

    }

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id)
        .orElseThrow(()->new ResourceNotFoundException("Category not found"));
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
