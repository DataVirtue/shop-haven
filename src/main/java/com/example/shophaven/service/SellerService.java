package com.example.shophaven.service;

import com.example.shophaven.dto.request.SellerRequestDto;
import com.example.shophaven.dto.response.SellerResponseDto;
import com.example.shophaven.model.Seller;
import com.example.shophaven.repository.SellerRepository;
import com.example.shophaven.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {


    @Autowired
    SellerRepository sellerRepository;

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {

        Seller seller = SellerTransformer.sellerRequestDtoToSeller(sellerRequestDto);

        Seller savedSeller = sellerRepository.save(seller);

        return SellerTransformer.sellerToSellerResponseDto(savedSeller);
    }


}
