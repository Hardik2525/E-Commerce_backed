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


import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name= "order_products")
@SQLDelete(sql = "UPDATE order_products SET deleted_at=CURRENT_TIMESTAMP where id=?")
@SQLRestriction("deleted_at is null")
public class OrderProduct extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false , name="order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false , name="product_id")
    private Product product;

    private Integer quantity;
}
