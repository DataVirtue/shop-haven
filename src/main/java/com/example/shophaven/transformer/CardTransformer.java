package com.example.shophaven.transformer;

import com.example.shophaven.dto.request.CardRequestDto;
import com.example.shophaven.dto.response.CardResponseDto;
import com.example.shophaven.model.Card;

public class CardTransformer {
    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){

        return  Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .validTill(cardRequestDto.getValidTill())
                .cvv(cardRequestDto.getCvv())
                .build();
    }
    public static String maskCardNo(String num){
        String maskedNum = "";
        for(int i=0;i<num.length()-4;i++){
            maskedNum+="x";
        }
        maskedNum += num.substring(num.length()-4);

        return maskedNum;
    }

    public static CardResponseDto cardToCardResponeDto (Card card){

        return  CardResponseDto.builder()
                .maskedNo(maskCardNo(card.getCardNo()))
                .cardType(card.getCardType())
                .validTill(card.getValidTill())
                .build();
    }
}
