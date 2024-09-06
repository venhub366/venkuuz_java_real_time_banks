package com.javaexpress.cards.service;

import com.javaexpress.cards.dto.CardsDTO;

public interface CardsService {
    String createCard(CardsDTO dto);

    CardsDTO getCardDetails(String mobileNumber);

    CardsDTO updateCardsInfo(CardsDTO dto);

    String deleteCard(String mobileNumber);
}
