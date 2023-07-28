package com.example.shophaven.dto.response;

import com.example.shophaven.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardResponseDto {
    String maskedNo;

    Date validTill;

    CardType cardType;
}
