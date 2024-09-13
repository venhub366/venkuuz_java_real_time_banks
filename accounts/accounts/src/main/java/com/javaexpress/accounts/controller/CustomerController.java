package com.javaexpress.accounts.controller;

import com.javaexpress.accounts.dto.CustomerDTO;
import com.javaexpress.accounts.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<CustomerDTO> fetchCustmerDetails(@RequestParam String mobileNumber){
      log.info("Feign client access demo for laons-cards");
        CustomerDTO dto = customerService.fetchCustmerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
