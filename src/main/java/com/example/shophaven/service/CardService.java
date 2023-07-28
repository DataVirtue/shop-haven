package com.example.shophaven.service;


import com.example.shophaven.dto.request.CardRequestDto;
import com.example.shophaven.dto.response.CardResponseDto;
import com.example.shophaven.dto.response.CustomerResponseDto;
import com.example.shophaven.exception.CustomerNotFoundException;
import com.example.shophaven.model.Card;
import com.example.shophaven.model.Customer;
import com.example.shophaven.repository.CardRepository;
import com.example.shophaven.repository.CustomerRepository;
import com.example.shophaven.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;
    public CardResponseDto addCard(CardRequestDto cardRequestDto) {

        Customer customer = customerRepository.findByMobNo(cardRequestDto.getCustomerMobile());

        if(customer==null){
            throw new CustomerNotFoundException("Customer mobile number not found");
        }

        Card card = CardTransformer.cardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        Card savedCard = cardRepository.save(card);

        return CardTransformer.cardToCardResponeDto(savedCard);

    }
}
