package com.javaexpress.loans.service;

import com.javaexpress.loans.dto.LoansDTO;

public interface LoanService {
    String createLoan(LoansDTO loansDTO);

    LoansDTO getLoanDetails(String mobileNumber);

    LoansDTO updateLoanInfo(LoansDTO dto);

    String deleteLoan(String mobileNumber);
}
