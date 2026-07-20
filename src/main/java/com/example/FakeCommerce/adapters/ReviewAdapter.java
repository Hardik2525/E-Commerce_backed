package com.example.FakeCommerce.adapters;

import org.springframework.stereotype.Component;

import com.example.FakeCommerce.dtos.GetReviewResponseDto;
import com.example.FakeCommerce.schema.Review;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewAdapter {
    public GetReviewResponseDto mapToReviewResponseDto(Review review){
        return GetReviewResponseDto.builder()
        .comment(review.getComment()).rating(review.getRating()).orderId(review.getOrder().getId()).productId(review.getProduct().getId()).build();
    }
}
