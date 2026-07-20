package com.example.FakeCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.FakeCommerce.schema.OrderProduct;
import com.example.FakeCommerce.schema.Order;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface OrderProductsRepository extends JpaRepository<OrderProduct,Long> {
        List<OrderProduct> findByOrderId(long orderid);

        @Query("SELECT op FROM OrderProduct op JOIN FETCH op.product WHERE op.order = :order")
        List<OrderProduct> findByOrderWithProduct(Order order);

}
