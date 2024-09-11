package com.javaexpress.cards.controller;

import com.javaexpress.cards.dto.CardsDTO;
import com.javaexpress.cards.service.CardsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cards")
@Validated
public class CardsController {

    @Autowired
    private CardsService cardsService;

    @PostMapping
    public ResponseEntity<String> createCard(@Valid @RequestBody CardsDTO dto) {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(cardsService.createCard(dto));

    }

    @GetMapping
    public ResponseEntity<CardsDTO> getCardDetails(@NotEmpty @Pattern(regexp = "(^$|[0-9]{10})") @RequestParam String mobileNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cardsService.getCardDetails(mobileNumber));
    }

    @PutMapping
    public ResponseEntity<CardsDTO> updateCardsInfo(@Valid @RequestBody CardsDTO dto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(cardsService.updateCardsInfo(dto));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCard(@RequestParam @NotEmpty @Pattern(regexp = "(^$|[0-9]{10})") String mobileNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cardsService.deleteCard(mobileNumber));
    }
}
