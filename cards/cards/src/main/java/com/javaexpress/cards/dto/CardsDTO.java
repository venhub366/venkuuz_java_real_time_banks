package com.javaexpress.cards.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardsDTO {
    @NotEmpty(message = "Mobile Num for Loan cannot be empty")
    @Pattern(message = "Enter a valid 10 digit Mobile Number", regexp = "(^$|[0-9]{10})")
    private String mobileNumber;
    private String cardNumber;
    private String cardType;

    private int totalLimit;
    private int amountUsed;
    private int availableAmount;
}
