package com.example.shophaven.service;


import com.example.shophaven.dto.request.CustomerRequestDto;
import com.example.shophaven.dto.response.CustomerResponseDto;
import com.example.shophaven.model.Cart;
import com.example.shophaven.model.Customer;
import com.example.shophaven.repository.CustomerRepository;
import com.example.shophaven.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {

        Customer customer = CustomerTransformer.customerRequestDtoToCustomer(customerRequestDto);
        customer.setCart(new Cart());
        customer.getCart().setCartTotal(0);
        customer.getCart().setCustomer(customer);


        Customer savedCustomer = customerRepository.save(customer);


        return CustomerTransformer.customerToCustomerResponseDto(savedCustomer);
    }
}
