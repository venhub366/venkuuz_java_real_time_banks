package com.javaexpress.accounts.service;

import com.javaexpress.accounts.dto.CustomerDTO;

public interface CustomerService {
    CustomerDTO fetchCustmerDetails(String mobileNumber);
}
