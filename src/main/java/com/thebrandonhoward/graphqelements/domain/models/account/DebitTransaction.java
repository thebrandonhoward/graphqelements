package com.thebrandonhoward.graphqelements.domain.models.account;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DebitTransaction {
    public String accountId;
    public BigDecimal debitAmount;
    public BankAccount.Currency currency;
}
