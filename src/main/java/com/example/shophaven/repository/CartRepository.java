package com.example.shophaven.repository;

import com.example.shophaven.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
}
