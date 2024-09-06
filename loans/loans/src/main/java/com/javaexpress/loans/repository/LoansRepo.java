package com.javaexpress.loans.repository;

import com.javaexpress.loans.entities.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoansRepo extends JpaRepository<Loans, Long> {
    Optional<Loans> findByMobileNumber(String mobileNumber);
}
