package com.javaexpress.accounts.service;

import com.javaexpress.accounts.dto.CustomerDTO;

public interface AccountsService {
    void createAccount(CustomerDTO dto);

    CustomerDTO fetchAccountInfo(String mobileNumber);

    CustomerDTO updateCustomerInfo(CustomerDTO dto);

    boolean deleteAccount(String mobileNumber);
}
