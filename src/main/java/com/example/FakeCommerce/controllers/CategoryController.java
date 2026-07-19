package com.example.FakeCommerce.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.FakeCommerce.dtos.CreateCategoryRequestDto;
import com.example.FakeCommerce.dtos.GetCategoryResponseDto;
import com.example.FakeCommerce.services.CategoryService;
import com.example.FakeCommerce.utils.ApiResponse;
import com.example.FakeCommerce.schema.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<Category>> createCategory(@RequestBody CreateCategoryRequestDto requestDto){
        Category category = categoryService.createCategory(requestDto);
        return ResponseEntity
        .status(HttpStatus.CREATED).
        body(ApiResponse.success(category,"Category created successfully"))
        ;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<GetCategoryResponseDto>>> getAllCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(categoryService.getAllCategories(),"fetched all categories successfully"));
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
}
