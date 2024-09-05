package com.javaexpress.accounts.service;

import com.javaexpress.accounts.dto.AccountsDTO;
import com.javaexpress.accounts.dto.CustomerDTO;
import com.javaexpress.accounts.entities.Accounts;
import com.javaexpress.accounts.entities.Customer;
import com.javaexpress.accounts.repository.AccountsRepo;
import com.javaexpress.accounts.repository.CusotmerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

/*
 * DTO <-> Entity conversion
 * 1. Manually
 * 2. ModelMapper
 * 3. Struts Library
 * 4. Spring class -> BeanUtils class
 * */


@Service
@Slf4j
public class AccountsServiceImpl implements AccountsService {
    @Autowired
    private AccountsRepo accountsRepo;

    @Autowired
    private CusotmerRepo cusotmerRepo;

    public void createAccount(CustomerDTO dto) {

        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);

        Optional<Customer> optioanl = cusotmerRepo.findByMobileNumber(dto.getMobileNumber());
        if (optioanl.isPresent()) {
            throw new RuntimeException("Customer Already Exixists with the given number");
        }
        Customer db_Customer = cusotmerRepo.save(customer);
        Accounts accounts = new Accounts();
        long randomNumber = 100000L + new Random().nextInt(9999999);
        accounts.setAccountNumber(randomNumber);
        accounts.setAccountType("SAVINGS");
        accounts.setBranchAddress("BASAVANAGARA");
        accounts.setCustomerId(db_Customer.getCusotmerId());
        accountsRepo.save(accounts);

    }

    public CustomerDTO fetchAccountInfo(String mobileNumber) {
        Customer db_Customer = cusotmerRepo.findByMobileNumber(mobileNumber).orElseThrow(() -> new RuntimeException("Mobile Number Not Found" + mobileNumber));
        Accounts db_Accounts = accountsRepo.findByCustomerId(db_Customer.getCusotmerId()).orElseThrow(() -> new RuntimeException("Account Number Not Found for Customer mobile number" + mobileNumber));

        CustomerDTO dto = new CustomerDTO();
        BeanUtils.copyProperties(db_Customer, dto);

        AccountsDTO accountsDTO = new AccountsDTO();
        BeanUtils.copyProperties(db_Accounts, accountsDTO);

        dto.setAccountsDTO(accountsDTO);

        return dto;
    }

    @Override
    public CustomerDTO updateCustomerInfo(CustomerDTO dto) {
        Customer db_Customer = cusotmerRepo.findByMobileNumber(dto.getMobileNumber()).orElseThrow(() -> new RuntimeException("Mobile Number Not Found" + dto.getMobileNumber()));

        BeanUtils.copyProperties(dto, db_Customer);
        System.out.println(db_Customer.toString());
        //Update Customer Info
        cusotmerRepo.save(db_Customer);
        AccountsDTO accountsDTO = dto.getAccountsDTO();
        Accounts db_accounts = accountsRepo.findByCustomerId(db_Customer.getCusotmerId()).orElseThrow(
                () -> new RuntimeException("No Account found for Customer " + db_Customer.getCusotmerId())
        );

        db_accounts.setAccountType(accountsDTO.getAccountType());
        db_accounts.setBranchAddress(accountsDTO.getBranchAddress());
        accountsRepo.save(db_accounts);

        BeanUtils.copyProperties(db_accounts, accountsDTO);
        BeanUtils.copyProperties(db_Customer, dto);

        dto.setAccountsDTO(accountsDTO);

        return dto;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {

        var customerDB = cusotmerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new RuntimeException("NO Customer exisits with the followeing number "+mobileNumber)
        );
        var accountsDB =accountsRepo.findByCustomerId(customerDB.getCusotmerId());
        accountsDB.ifPresent(accounts -> accountsRepo.delete(accounts));

      //  accountsRepo.deleteByCustomerId(customerDB.getCusotmerId());
        cusotmerRepo.deleteById(customerDB.getCusotmerId());

        return true;
    }
}
