package com.javaexpress.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoansDTO {
    private String loanNumber;
    @NotEmpty(message = "Mobile Num for Loan cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Enter a valid 10 Digit Mobile Number")
    private String mobileNumber;
    private String loanType;

    private int totalLoan;
    private int amountPaid;
    private int outstandingAmount;
}
