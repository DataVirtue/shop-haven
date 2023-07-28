package com.example.shophaven.transformer;


import com.example.shophaven.dto.request.CustomerRequestDto;
import com.example.shophaven.dto.response.CustomerResponseDto;
import com.example.shophaven.model.Customer;

public class CustomerTransformer {

    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){

        return Customer.builder()
                .name(customerRequestDto.getName())
                .emailId(customerRequestDto.getEmailId())
                .mobNo(customerRequestDto.getMobNo())
                .gender(customerRequestDto.getGender())
                .age(customerRequestDto.getAge())
                .build();
        }

    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer){

        return CustomerResponseDto.builder()
                .name(customer.getName())
                .emailId(customer.getEmailId())
                .mobNo(customer.getMobNo())
                .gender(customer.getGender())
                .build();
    }
}
