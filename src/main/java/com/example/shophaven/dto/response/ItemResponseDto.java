package com.example.shophaven.dto.response;


import com.example.shophaven.Enum.Category;
import com.example.shophaven.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemResponseDto {
    String itemName;

    int itemPrice;

    int quantityAdded;

    Category productCategory;


}
