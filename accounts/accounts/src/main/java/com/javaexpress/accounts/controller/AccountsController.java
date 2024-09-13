package com.javaexpress.accounts.controller;

import com.javaexpress.accounts.dto.CustomerDTO;
import com.javaexpress.accounts.service.AccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@Validated
@Slf4j
public class AccountsController {


    @Autowired
    private AccountsService accountsService;

    @PostMapping
    public ResponseEntity<String> createAccount(@Valid @RequestBody CustomerDTO dto) {
        log.info("Creating account for {}", dto.getMobileNumber());
        accountsService.createAccount(dto);
        log.info("Account cerated successfully for {}", dto.getMobileNumber());
        return ResponseEntity.status(HttpStatus.OK)
                .body("Account Created Successfully");
    }

    @GetMapping
    public ResponseEntity<CustomerDTO> fetchAccountInfo(@RequestParam(required = true) @Size(max = 10, message = "mob num should be 10 digits") String mobileNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountsService.fetchAccountInfo(mobileNumber));
    }

    @PutMapping
    public ResponseEntity<CustomerDTO> updateAccountInfo(@RequestBody CustomerDTO dto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(accountsService.updateCustomerInfo(dto));
    }

    @DeleteMapping
    public boolean deleteAccountInfo(@RequestParam String mobileNumber) {
        return accountsService.deleteAccount(mobileNumber);
    }
}
