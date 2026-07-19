package com.example.FakeCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.FakeCommerce.schema.OrderProduct;

@Repository
public interface OrderProductsRepository extends JpaRepository<OrderProduct,Long> {
        List<OrderProduct> findByOrderId(long orderid);
}
