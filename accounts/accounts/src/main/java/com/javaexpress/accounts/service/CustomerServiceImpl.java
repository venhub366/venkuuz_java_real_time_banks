package com.javaexpress.accounts.service;

import com.javaexpress.accounts.dto.AccountsDTO;
import com.javaexpress.accounts.dto.CardsDTO;
import com.javaexpress.accounts.dto.CustomerDTO;
import com.javaexpress.accounts.dto.LoansDTO;
import com.javaexpress.accounts.entities.Accounts;
import com.javaexpress.accounts.entities.Customer;
import com.javaexpress.accounts.feignClients.CardsFeignClient;
import com.javaexpress.accounts.feignClients.LoansFeignClient;
import com.javaexpress.accounts.repository.AccountsRepo;
import com.javaexpress.accounts.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {


    private CustomerRepo customerRepo;
    private AccountsRepo accountsRepo;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDTO fetchCustmerDetails(String mobileNumber) {
        log.info("fetch customer details");
        var cust_DB = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new RuntimeException("Customer no found with mob number " + mobileNumber)
        );
        var acc_DB = accountsRepo.findByCustomerId(cust_DB.getCusotmerId()).orElseThrow(
                () -> new RuntimeException("Account not found with Customer ID" + cust_DB.getCusotmerId())
        );
        CardsDTO cards_DTO = cardsFeignClient.getCardDetails(mobileNumber).getBody();
        LoansDTO loans_DTO = loansFeignClient.getLoanDetails(mobileNumber).getBody();

        return getCustomerDTO(cust_DB, acc_DB, cards_DTO, loans_DTO);

    }

    private static CustomerDTO getCustomerDTO(Customer cust_DB, Accounts acc_DB, CardsDTO cards_DTO, LoansDTO loans_DTO) {

        CustomerDTO cust_DTO = new CustomerDTO();
        BeanUtils.copyProperties(cust_DB, cust_DTO);

        AccountsDTO acc_DTO = new AccountsDTO();
        BeanUtils.copyProperties(acc_DB, acc_DB);
        cust_DTO.setAccountsDTO(acc_DTO);

        if (cards_DTO != null) {
            cust_DTO.setCardsDTO(cards_DTO);
        }
        if (loans_DTO != null) {
            cust_DTO.setLoansDTO(loans_DTO);
        }
        return cust_DTO;
    }
}
