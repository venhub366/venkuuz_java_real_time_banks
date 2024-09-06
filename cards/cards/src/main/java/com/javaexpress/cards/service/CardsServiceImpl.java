package com.javaexpress.cards.service;

import com.javaexpress.cards.dto.CardsDTO;
import com.javaexpress.cards.entities.Cards;
import com.javaexpress.cards.repository.CardsRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CardsServiceImpl implements CardsService {

    @Autowired
    private CardsRepo cardsRepo;

    @Override
    public String createCard(CardsDTO dto) {
        Cards entity = new Cards();
        Optional<Cards> db_Cards = cardsRepo.findByMobileNumber(dto.getMobileNumber());
        if (db_Cards.isPresent()) {
            throw new RuntimeException("Cards already exists for the mobile number" + db_Cards.get().getMobileNumber());
        }
        BeanUtils.copyProperties(dto, entity);
        cardsRepo.save(entity);
        return "Card Successfully created ";
    }

    @Override
    public CardsDTO getCardDetails(String mobileNumber) {

        Cards db_card = cardsRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new RuntimeException(" Cards not found for the mobile number" + mobileNumber)
        );
        CardsDTO dto = new CardsDTO();
        BeanUtils.copyProperties(db_card, dto);
        return dto;
    }

    @Override
    public CardsDTO updateCardsInfo(CardsDTO dto) {
        Cards db_card = cardsRepo.findByMobileNumber(dto.getMobileNumber()).orElseThrow(
                () -> new RuntimeException(" Cards not found for the mobile number" + dto.getMobileNumber())
        );
        BeanUtils.copyProperties(dto, db_card);
        db_card = cardsRepo.save(db_card);
        BeanUtils.copyProperties(db_card, dto);
        return dto;
    }

    @Override
    public String deleteCard(String mobileNumber) {
        Cards db_card = cardsRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new RuntimeException(" Cards not found for the mobile number" + mobileNumber)
        );
        cardsRepo.delete(db_card);
        return "Card Delete Successfully";
    }
}
