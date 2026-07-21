package com.example.FakeCommerce.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.FakeCommerce.dtos.CreateReviewRequestDto;
import com.example.FakeCommerce.dtos.GetReviewResponseDto;
import com.example.FakeCommerce.exceptions.ResourceNotFoundException;
import com.example.FakeCommerce.repositories.OrderRepository;
import com.example.FakeCommerce.repositories.ProductRepository;
import com.example.FakeCommerce.repositories.ReviewRepository;
import com.example.FakeCommerce.schema.Review;
import com.example.FakeCommerce.schema.*;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    public Review createReview(CreateReviewRequestDto createReviewRequestDto){
        Order order = orderRepository.findById(createReviewRequestDto.getOrderId()).
        orElseThrow(() -> new ResourceNotFoundException("Order id is non existent in DB :" + createReviewRequestDto.getOrderId()));
        Product product = productRepository.findById(createReviewRequestDto.getProductId())
        .orElseThrow(() -> new ResourceNotFoundException("Product Id is non existent in DB"));
        Review review = Review.builder()
                        .order(order)
                        .product(product)
                        .comment(createReviewRequestDto.getComment())
                        .rating(createReviewRequestDto.getRating())
                        .build();
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public void deleteReview(Long id){
        reviewRepository.deleteById(id);
        }

    public Review getReviewById(Long id){
        return reviewRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Review not found in DB"));
    }

    public List<Review> getReviewsByProductId(Long productId){
        return reviewRepository.findByProductId(productId);
    } 

    public List<Review> getReviewsByOrderId(Long orderId){
        return reviewRepository.findByOrderId(orderId);
    } 
}
