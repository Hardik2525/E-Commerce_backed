package com.example.FakeCommerce.services;
import org.springframework.stereotype.Service;

import com.example.FakeCommerce.adapters.OrderAdapter;
import com.example.FakeCommerce.dtos.CreateOrderRequestDto;
import com.example.FakeCommerce.dtos.GetOrderResponseDto;
import com.example.FakeCommerce.exceptions.ResourceNotFoundException;
import com.example.FakeCommerce.repositories.OrderProductsRepository;
import com.example.FakeCommerce.repositories.OrderRepository;
import com.example.FakeCommerce.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import com.example.FakeCommerce.schema.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductsRepository orderProductsRepository;
    private final ProductRepository  productRepository;
    private final OrderAdapter orderAdapter;

    public List<GetOrderResponseDto> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        // List<Order> -> List<GetOrderResponseDto>
        return orderAdapter.mapToGetOrderResponseDtoList(orders);
    }

    public GetOrderResponseDto getOrderByid(Long id){
        Order order = orderRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return orderAdapter.mapToGetOrderResponseDto(order);

    }

    public void createOrder(CreateOrderRequestDto orderRequestDto){
        Order order = Order.builder()
        .Status(OrderStatus.PENDING)
        .build();
         orderRepository.save(order);

        if(orderRequestDto.getOrderItems() != null){
                for(var itemDto : orderRequestDto.getOrderItems()){
                    Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(()-> new ResourceNotFoundException("Order id not found : " + itemDto.getProductId()));            

                    OrderProduct orderProduct = OrderProduct.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemDto.getQuantity() !=null ? itemDto.getQuantity() : 1)
                    .build();
                orderProductsRepository.save(orderProduct);
            }
        }

    }

    public void deleteOrder(Long id){
        Order order = orderRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        orderRepository.delete(order);
    }
}
