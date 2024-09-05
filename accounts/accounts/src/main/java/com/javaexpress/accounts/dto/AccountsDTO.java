package com.javaexpress.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDTO {

    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
