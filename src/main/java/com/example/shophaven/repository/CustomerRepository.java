package com.example.shophaven.repository;

import com.example.shophaven.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

//    @Query(value = "select customer.* from customers inner join order_info on customer.id=order_info.customer_id where customer.age>19 and customer.age<31 and customer.gender=FEMALE group by customer.id order by count(order_info.id) desc where count(order_info.id)>:k")
//    public List<Customer> getAllFemaleCustomersWithOrdersK(int age, int k);

    Customer findByMobNo(String mobNo);

    Customer findByEmailId(String emailId);


}
