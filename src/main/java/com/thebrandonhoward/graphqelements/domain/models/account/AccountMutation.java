package com.thebrandonhoward.graphqelements.domain.models.account;

import lombok.Data;

@Data
public class AccountMutation {
    public BankAccount creditAccount;
    public BankAccount debitAccount;
}
