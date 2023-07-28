package com.example.shophaven.repository;

import com.example.shophaven.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
}
