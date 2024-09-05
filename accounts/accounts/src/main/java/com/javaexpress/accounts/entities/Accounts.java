package com.javaexpress.accounts.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {

    @Id
    private Long accountNumber; // generate a random 10 dig acc number
    private Long customerId;
    private String accountType;
    private String branchAddress;

}
