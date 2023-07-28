package com.example.shophaven.repository;

import com.example.shophaven.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

//    @Query(value = "select * from product where category=:category order by price asc limit 5",nativeQuery = true)
//    public List<Product> getCheapestProductsInACategory(String category);
}
