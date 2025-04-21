package com.thebrandonhoward.graphqelements.adapters.account;

import com.thebrandonhoward.graphqelements.adapters.repositories.BankAccountRepository;
import com.thebrandonhoward.graphqelements.domain.models.account.AccountApplication;
import com.thebrandonhoward.graphqelements.domain.models.account.BankAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateBankAccountAdapter {
    private final  BankAccountRepository bankAccountRepository;

    public BankAccount createBankAccount(AccountApplication accountApplication) {
        BankAccount bankAccount = BankAccount.createBankAccount(accountApplication);

        com.thebrandonhoward.graphqelements.adapters.entities.BankAccount bankAccountEntity
                = new com.thebrandonhoward.graphqelements.adapters.entities.BankAccount();
        bankAccountEntity.setBalance(bankAccount.getBalance());
        bankAccountEntity.setCurrency(bankAccount.getCurrency().name());
        bankAccountEntity.setStatus(bankAccount.getStatus());

        com.thebrandonhoward.graphqelements.adapters.entities.BankAccount save
                = bankAccountRepository.save(bankAccountEntity);

        return BankAccount.builder()
                .id(save.getId().toString())
                .currency(BankAccount.Currency.valueOf(save.getCurrency()))
                .client(bankAccount.getClient())
                .balance(save.getBalance())
                .status(save.getStatus())
                .build();
    }
}
