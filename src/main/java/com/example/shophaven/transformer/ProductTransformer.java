package com.example.shophaven.transformer;

import com.example.shophaven.Enum.ProductStatus;
import com.example.shophaven.dto.request.ProductRequestDto;
import com.example.shophaven.dto.response.ProductResponseDto;
import com.example.shophaven.model.Product;

public class ProductTransformer {

    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .productName(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .availableQuantity(productRequestDto.getAvailableQuantity())
                .category(productRequestDto.getProductCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto productToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .sellerEmail(product.getSeller().getEmailId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .availableQuantity(product.getAvailableQuantity())
                .productCategory(product.getCategory())
                .productStatus(product.getProductStatus())
                .build();
    }
}
