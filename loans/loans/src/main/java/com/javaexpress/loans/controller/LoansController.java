package com.javaexpress.loans.controller;

import com.javaexpress.loans.dto.LoansDTO;
import com.javaexpress.loans.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loans")
public class LoansController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<String> createLoan(@RequestBody LoansDTO loansDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loanService.createLoan(loansDTO));
    }

    @GetMapping
    public ResponseEntity<LoansDTO> getLoanDetails(@RequestParam String mobileNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(loanService.getLoanDetails(mobileNumber));
    }

    @PutMapping
    public ResponseEntity<LoansDTO> updateLoanInfo(@RequestBody LoansDTO dto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(loanService.updateLoanInfo(dto));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLoan(@RequestParam String mobileNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(loanService.deleteLoan(mobileNumber));
    }
}
