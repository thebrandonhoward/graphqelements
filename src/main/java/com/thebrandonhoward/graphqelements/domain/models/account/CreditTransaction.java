package com.thebrandonhoward.graphqelements.domain.models.account;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreditTransaction {
    public String accountId;
    public BigDecimal creditAmount;
    public BankAccount.Currency currency;
}
