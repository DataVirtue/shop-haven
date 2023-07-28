package com.example.shophaven.transformer;

import com.example.shophaven.dto.request.ItemRequestDto;
import com.example.shophaven.dto.response.ItemResponseDto;
import com.example.shophaven.model.Item;

public class ItemTransformer {

    public static Item itemRequestDtoToItem(ItemRequestDto itemRequestDto){

        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();
    }

    public static ItemResponseDto itemToItemResponseDto(Item saveditem) {

        return ItemResponseDto.builder()
                .quantityAdded(saveditem.getRequiredQuantity())
                .itemName(saveditem.getProduct().getProductName())
                .itemPrice(saveditem.getProduct().getPrice()*saveditem.getRequiredQuantity())
                .productCategory(saveditem.getProduct().getCategory())
                .build();

    }
}
