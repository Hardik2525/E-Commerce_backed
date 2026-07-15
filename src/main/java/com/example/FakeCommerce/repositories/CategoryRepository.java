package com.example.FakeCommerce.repositories;

import com.example.FakeCommerce.schema.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{
    
}
