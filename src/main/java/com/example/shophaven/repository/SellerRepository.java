package com.example.shophaven.repository;

import com.example.shophaven.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {

    public Seller findByEmailId(String emailId);

//    @Query("select seller.* from seller inner join product on seller.id=product.seller_id where product.price= (select max(price) from product)")
//    List<Seller> getSellersWithTheCheapestProduct();
}
