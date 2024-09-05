package com.javaexpress.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTO {
    private String name;
    private String email;
    private String mobileNumber;
    AccountsDTO accountsDTO;
}
