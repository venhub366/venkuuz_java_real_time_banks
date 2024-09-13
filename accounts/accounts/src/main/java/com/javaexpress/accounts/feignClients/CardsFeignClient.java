package com.javaexpress.accounts.feignClients;

import com.javaexpress.accounts.dto.CardsDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "jt-cards",path = "/api/v1/cards")
@LoadBalancerClient(name = "CARDS_LB")
public interface CardsFeignClient {
    //@GetMapping("/api/v1/cards")
    @GetMapping
    public ResponseEntity<CardsDTO> getCardDetails(@NotEmpty @Pattern(regexp = "(^$|[0-9]{10})") @RequestParam String mobileNumber);
}
