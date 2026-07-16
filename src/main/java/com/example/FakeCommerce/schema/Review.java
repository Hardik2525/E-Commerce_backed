package com.example.FakeCommerce.schema;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "reviews")
@SQLDelete(sql = "UPDATE reviews SET deleted_at=CURRENT_TIMESTAMP where id=?")
@SQLRestriction("deleted_at is null")
public class Review extends BaseEntity{

    @Column(nullable = false)
    private BigDecimal rating;
    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(nullable = false , name="order_id")
    private Order order;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(nullable = false , name="product_id")
    private Product product;
}
