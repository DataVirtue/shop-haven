package com.example.shophaven.service;


import com.example.shophaven.Enum.Category;
import com.example.shophaven.dto.request.ProductRequestDto;
import com.example.shophaven.dto.response.ProductResponseDto;
import com.example.shophaven.exception.SellerNotFoundException;
import com.example.shophaven.model.Product;
import com.example.shophaven.model.Seller;
import com.example.shophaven.repository.ProductRepository;
import com.example.shophaven.repository.SellerRepository;
import com.example.shophaven.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {

        Seller seller = sellerRepository.findByEmailId(productRequestDto.getSellerEmail());

        if(seller==null)
            throw new SellerNotFoundException("Seller emailId not found");

        Product product = ProductTransformer.productRequestDtoToProduct(productRequestDto);

        product.setSeller(seller);
        Product savedProduct = productRepository.save(product);

        return ProductTransformer.productToProductResponseDto(savedProduct);

        }

//    public List<ProductResponseDto> getCheapestProductsInACategory(Category cat){
//        List<Product> productList = productRepository.getCheapestProductsInACategory(cat.toString());
//        List<ProductResponseDto> ans = new ArrayList<>();
//        for(Product product: productList){
//            ans.add(ProductTransformer.productToProductResponseDto(product));
//        }
//        return ans;
//    }
}
