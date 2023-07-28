package com.example.shophaven.dto.response;


import com.example.shophaven.Enum.Category;
import com.example.shophaven.Enum.ProductStatus;
import com.example.shophaven.model.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {

    String sellerEmail;

    String productName;

    int price;

    int availableQuantity;

    Category productCategory;
    ProductStatus productStatus;

}
