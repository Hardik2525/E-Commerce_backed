package com.example.FakeCommerce.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;

import com.example.FakeCommerce.adapters.ReviewAdapter;
import com.example.FakeCommerce.dtos.CreateReviewRequestDto;
import com.example.FakeCommerce.dtos.GetReviewResponseDto;
import com.example.FakeCommerce.schema.Review;
import com.example.FakeCommerce.services.ReviewService;
import com.example.FakeCommerce.utils.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewAdapter reviewAdapter;
    @GetMapping
    public List<Review> getAllReviews() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @PostMapping
    public ResponseEntity<ApiResponse<GetReviewResponseDto>> createReview(@RequestBody CreateReviewRequestDto createReviewRequestDto) {
        Review review = reviewService.createReview(createReviewRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(reviewAdapter.mapToReviewResponseDto(review), "Review created successfully"));
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @GetMapping("/product/{productId}")
    public List<Review> getReviewsByProductId(@PathVariable Long productId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @GetMapping("/order/{orderId}")
    public List<Review> getReviewsByOrderId(@PathVariable Long orderId) {
        throw new UnsupportedOperationException("Not implemented");
    }
}