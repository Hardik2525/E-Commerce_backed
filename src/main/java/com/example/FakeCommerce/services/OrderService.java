package com.example.FakeCommerce.services;
import org.springframework.stereotype.Service;

import com.example.FakeCommerce.adapters.OrderAdapter;
import com.example.FakeCommerce.dtos.CreateOrderRequestDto;
import com.example.FakeCommerce.dtos.GetOrderResponseDto;
import java.util.stream.Collectors;
import com.example.FakeCommerce.exceptions.ResourceNotFoundException;
import com.example.FakeCommerce.repositories.OrderProductsRepository;
import com.example.FakeCommerce.repositories.OrderRepository;
import com.example.FakeCommerce.repositories.ProductRepository;
import java.util.function.Function;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.FakeCommerce.schema.*;

import jakarta.transaction.Transactional;
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

    @Transactional
    public GetOrderResponseDto createOrder(CreateOrderRequestDto orderRequestDto){
        Order order = Order.builder()
        .Status(OrderStatus.PENDING)
        .build();
         orderRepository.save(order);

        if(orderRequestDto.getOrderItems() != null){
            List<Long> productIds = orderRequestDto.getOrderItems().stream().map(item -> item.getProductId()).collect(Collectors.toList());
                List<Product> products = productRepository.findAllById(productIds);

                Map<Long,Product> productMap = products.stream().collect(Collectors.toMap(Product::getId,Function.identity()));
                for(Long id : productIds){
                    if(!productMap.containsKey(id)){
                        throw new ResourceNotFoundException("Product id not found : " + id);
                    }
                }
                List<OrderProduct> orderProducts =  new ArrayList<>();
                for(var itemDto : orderRequestDto.getOrderItems()){
                    Product product = productMap.get(itemDto.getProductId());

                    orderProducts.add((OrderProduct.builder().product(product).order(order)
                    .quantity(itemDto.getQuantity() !=null ? itemDto.getQuantity() : 1))
                    .build());
                }
                orderProductsRepository.saveAll(orderProducts);
        }
        return orderAdapter.mapToGetOrderResponseDto(order);
    }

    public void deleteOrder(Long id){
        Order order = orderRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        orderRepository.delete(order);
    }
}
