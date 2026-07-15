package com.example.FakeCommerce.schema;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name= "order_products")
public class OrderProduct extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false , name="order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false , name="product_id")
    private Product product;

    private Integer quantity;
}
