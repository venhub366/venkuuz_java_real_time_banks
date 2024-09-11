package com.javaexpress.loans.controller;

import com.javaexpress.loans.dto.LoansDTO;
import com.javaexpress.loans.service.LoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loans")
@Validated
public class LoansController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<String> createLoan(@Valid @RequestBody LoansDTO loansDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loanService.createLoan(loansDTO));
    }

    @GetMapping
    public ResponseEntity<LoansDTO> getLoanDetails(@NotEmpty @Pattern(regexp = "(^$|[0-9]{10})", message = "Enter valid message") @RequestParam String mobileNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(loanService.getLoanDetails(mobileNumber));
    }

    @PutMapping
    public ResponseEntity<LoansDTO> updateLoanInfo(@Valid @RequestBody LoansDTO dto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(loanService.updateLoanInfo(dto));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLoan(@RequestParam @NotEmpty @Pattern(regexp = "(^$|[0-9]{10})", message = "Enter a valid message") String mobileNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(loanService.deleteLoan(mobileNumber));
    }
}
