package com.example.shophaven.dto.response;


import com.example.shophaven.model.Item;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {

    String customerEmail;

    String orderId;

    String cardUsed;

    Date orderDate;

    int orderTotal;

    List<ItemResponseDto> itemsList = new ArrayList<>();

}
