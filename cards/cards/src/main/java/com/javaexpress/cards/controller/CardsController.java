package com.javaexpress.cards.controller;

import com.javaexpress.cards.dto.CardsDTO;
import com.javaexpress.cards.service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cards")
public class CardsController {

    @Autowired
    private CardsService cardsService;

    @PostMapping
    public ResponseEntity<String> createCard(@RequestBody CardsDTO dto) {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(cardsService.createCard(dto));

    }

    @GetMapping
    public ResponseEntity<CardsDTO> getCardDetails(@RequestParam String mobileNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cardsService.getCardDetails(mobileNumber));
    }

    @PutMapping
    public ResponseEntity<CardsDTO> updateCardsInfo(@RequestBody CardsDTO dto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(cardsService.updateCardsInfo(dto));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCard(@RequestParam String mobileNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cardsService.deleteCard(mobileNumber));
    }
}
