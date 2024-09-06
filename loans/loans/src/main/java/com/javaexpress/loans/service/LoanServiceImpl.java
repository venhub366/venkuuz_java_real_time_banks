package com.javaexpress.loans.service;

import com.javaexpress.loans.dto.LoansDTO;
import com.javaexpress.loans.entities.Loans;
import com.javaexpress.loans.repository.LoansRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoansRepo loansRepo;

    @Override
    public String createLoan(LoansDTO loansDTO) {
        Optional<Loans> db_Loan = loansRepo.findByMobileNumber(loansDTO.getMobileNumber());
        if (db_Loan.isPresent()) {
            throw new RuntimeException("Loan already exists with the mobile number" + loansDTO.getMobileNumber());
        }
        Loans loan = new Loans();
        BeanUtils.copyProperties(loansDTO, loan);
        loansRepo.save(loan);
        return "Loan Successfully created";
    }

    @Override
    public LoansDTO getLoanDetails(String mobileNumber) {
        var loan = loansRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new RuntimeException("Loan not found with the mobile number" + mobileNumber)
        );
        var dto = new LoansDTO();
        BeanUtils.copyProperties(loan, dto);
        return dto;
    }

    @Override
    public LoansDTO updateLoanInfo(LoansDTO dto) {
        var db_loan = loansRepo.findByMobileNumber(dto.getMobileNumber()).orElseThrow(
                () -> new RuntimeException("Loan not found for the mobile number")
        );
        BeanUtils.copyProperties(dto, db_loan);
        loansRepo.save(db_loan);
        BeanUtils.copyProperties(db_loan, dto);
        return dto;
    }

    @Override
    public String deleteLoan(String mobileNumber) {
        var loan = loansRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new RuntimeException("Loan not found with the mobile number" + mobileNumber)
        );
        loansRepo.delete(loan);
        return "Loan delete successfully";
    }
}
