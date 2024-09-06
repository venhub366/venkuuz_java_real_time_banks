package com.javaexpress.loans.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoansDTO {
    private String loanNumber;
    private String mobileNumber;
    private String loanType;

    private int totalLoan;
    private int amountPaid;
    private int outstandingAmount;
}
