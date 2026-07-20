package com.example.FakeCommerce.services;
import org.springframework.stereotype.Service;

import com.example.FakeCommerce.adapters.OrderAdapter;
import com.example.FakeCommerce.dtos.CreateOrderRequestDto;
import com.example.FakeCommerce.dtos.GetOrderResponseDto;
import com.example.FakeCommerce.dtos.UpdateOrderRequestDto;

import java.util.stream.Collectors;
import com.example.FakeCommerce.exceptions.ResourceNotFoundException;
import com.example.FakeCommerce.repositories.OrderProductsRepository;
import com.example.FakeCommerce.repositories.OrderRepository;
import com.example.FakeCommerce.repositories.ProductRepository;
import java.util.function.Function;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.FakeCommerce.dtos.*;

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

    public GetOrderResponseDto updateOrder(Long id , UpdateOrderRequestDto updateOrderRequestDto){
        Order order = orderRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User Id not found"));

        if(updateOrderRequestDto.getStatus()!=null){
            order.setStatus(updateOrderRequestDto.getStatus());
            orderRepository.save(order);
        }

        if(updateOrderRequestDto.getOrderItems() != null){
            List<Long> productIds = updateOrderRequestDto.getOrderItems().stream().map(item -> item.getProductId()).collect(Collectors.toList());
            List<Product> products = productRepository.findAllById(productIds);
            Map<Long,Product> productMap = products.stream().collect(Collectors.toMap(Product::getId,Function.identity()));
            for(var pid : productIds){
                if(!productMap.containsKey(pid)){
                    throw new ResourceNotFoundException("Product not found");
                }
            }

            List<OrderProduct> toSave = new ArrayList<>();
            List<OrderProduct> toDelete = new ArrayList<>();

            Map<Long,OrderProduct> existingItems = orderProductsRepository.findByOrderWithProduct(order)
            .stream().collect(Collectors.toMap(op->op.getProduct().getId(),Function.identity()));

            for(OrderItemActionDto itemAction : updateOrderRequestDto.getOrderItems()){
                Product product = productMap.get(itemAction.getProductId());
                OrderProduct existing = existingItems.get(product.getId());
                
                switch(itemAction.getAction()){
                    case ADD :
                            if(existing != null){
                                int addQty = (itemAction.getQuantity() != null ? itemAction.getQuantity() : 1);
                                existing.setQuantity(existing.getQuantity() + addQty);
                                toSave.add(existing);
                            }else{
                                OrderProduct newItem = OrderProduct.builder().order(order).product(product).quantity(itemAction.getQuantity() != null ? itemAction.getQuantity() : 1).build();
                                existingItems.put(product.getId(),newItem);
                            }
                         
                    case REMOVE :
                            if(existing == null){
                                throw new ResourceNotFoundException("Product not found with ID: " + product.getId());
                            }
                            toDelete.add(existing);
                            existingItems.remove(product.getId());
                    case INCREMENT :
                        if(existing == null){
                            throw new ResourceNotFoundException("Product not found with ID: " + product.getId());
                        }
                        existing.setQuantity(existing.getQuantity()+1);
                        toSave.add(existing);
                    case DECREMENT :
                        if(existing == null){
                            throw new ResourceNotFoundException("Product not found with ID: " + product.getId());
                        }
                        if(existing.getQuantity()<=1){
                            toDelete.add(existing);
                            existingItems.remove(product.getId());
                        }else{
                        existing.setQuantity(existing.getQuantity()-1);
                        toSave.add(existing);
                        }
                }
            }
            if(!toSave.isEmpty()){
                orderProductsRepository.saveAll(toSave);
            }
            if(!toDelete.isEmpty()){
                orderProductsRepository.deleteAll(toDelete);
            }
        }

        return orderAdapter.mapToGetOrderResponseDto(order);
    }

    public void deleteOrder(Long id){
        Order order = orderRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        orderRepository.delete(order);
    }

    public GetOrderSummaryResponseDto getOrderSummary(Long id){
        Order order = orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Order not found in DB"));
        List<OrderProduct> orderProducts = orderProductsRepository.findByOrderWithProduct(order);

        List<OrderItemResponseDto> items = orderAdapter.mapToOrderItemResponseDtos(orderProducts);

        int totalItems = orderProducts.stream().mapToInt(OrderProduct::getQuantity).sum();
        BigDecimal totalPrice = orderProducts.stream().map(op -> op.getProduct().getPrice().multiply(BigDecimal.valueOf(op.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add); 

        return GetOrderSummaryResponseDto.builder()
        .id(order.getId())
        .status(order.getStatus())
        .items(items)
        .totalItems(totalItems)
        .totalprice(totalPrice)
        .createdAt(order.getCreatedAt())
        .updatedAt(order.getUpdatedAt())
        .build();
    }
}
