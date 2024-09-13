package com.javaexpress.accounts.feignClients;

import com.javaexpress.accounts.dto.LoansDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "jt-loans",path = "/api/v1/loans")
@LoadBalancerClient(name = "LOANS_LB")
public interface LoansFeignClient {
    @GetMapping
    public ResponseEntity<LoansDTO> getLoanDetails(@NotEmpty @Pattern(regexp = "(^$|[0-9]{10})", message = "Enter valid message") @RequestParam String mobileNumber);
}
