package com.thebrandonhoward.graphqelements.domain.models.account;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class AccountApplication {
    public Client client;
    public BankAccount.Currency currency;
    public BigDecimal balance;
}
