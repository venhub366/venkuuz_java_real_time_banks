package com.javaexpress.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTO {
    private String name;
    private String email;
    @NotEmpty(message = "Mobile Num for Loan cannot be empty")
   // @Size(min = 10, max = 10, message = "Enter a valid 10 digit Mobile Number")
    @Pattern(message = "Enter a valid 10 Digit Mobile Number", regexp = "(^$|[0-9]{10})")
    private String mobileNumber;
    AccountsDTO accountsDTO;
}
