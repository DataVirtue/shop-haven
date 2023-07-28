package com.example.shophaven.repository;

import com.example.shophaven.model.OrderEntity;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity,Integer> {


    public OrderEntity findByOrderId(String orderId);

    @Query(value = "select * from order_info order by order_total desc limit 5",nativeQuery = true)
    public List<OrderEntity> getTopFiveOrders();



}
