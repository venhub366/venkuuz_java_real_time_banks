package com.javaexpress.loans.dto;

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
    @Pattern(message = "Enter a valid 10 digit Mobile Number", regexp = "(^$|[0-9]{10})")
    private String mobileNumber;
    private String loanType;

    private int totalLoan;
    private int amountPaid;
    private int outstandingAmount;
}
