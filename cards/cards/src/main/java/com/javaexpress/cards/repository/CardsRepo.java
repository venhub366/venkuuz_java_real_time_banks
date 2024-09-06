package com.javaexpress.cards.repository;

import com.javaexpress.cards.entities.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardsRepo extends JpaRepository<Cards,Long> {
    Optional<Cards> findByMobileNumber(String mobileNumber);
}
