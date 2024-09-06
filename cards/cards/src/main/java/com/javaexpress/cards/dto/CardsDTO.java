package com.javaexpress.cards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardsDTO {
    private String mobileNumber;
    private String cardNumber;
    private String cardType;

    private int totalLimit;
    private int amountUsed;
    private int availableAmount;
}
