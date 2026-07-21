package com.example.FakeCommerce.adapters;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<GetReviewResponseDto> mapToReviewResponseDtoList(List<Review> reviews){
        return reviews.stream().map(this::mapToReviewResponseDto).collect(Collectors.toList());
    }
}
