package com.example.FakeCommerce.schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "orders")
@SQLDelete(sql = "UPDATE orders SET deleted_at=CURRENT_TIMESTAMP where id=?")
@SQLRestriction("deleted_at is null")
public class Order extends BaseEntity {
    
    private OrderStatus Status;
}
