package com.thebrandonhoward.graphqelements.domain.models.account;

import lombok.Data;

import java.util.List;

@Data
public class AccountQuery {
    public List<BankAccount> accounts;
    public BankAccount accountById;
}
