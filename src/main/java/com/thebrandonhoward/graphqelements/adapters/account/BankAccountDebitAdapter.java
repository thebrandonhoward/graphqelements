package com.thebrandonhoward.graphqelements.adapters.account;

import com.thebrandonhoward.graphqelements.adapters.repositories.BankAccountRepository;
import com.thebrandonhoward.graphqelements.domain.models.account.BankAccount;
import com.thebrandonhoward.graphqelements.domain.models.account.DebitTransaction;
import com.thebrandonhoward.graphqelements.domain.models.mocks.MockClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class BankAccountDebitAdapter {
    private final  BankAccountRepository bankAccountRepository;

    public BankAccount debitBankAccount(DebitTransaction debitTransaction) {
        com.thebrandonhoward.graphqelements.adapters.entities.BankAccount rootBankAccount
                = bankAccountRepository.getBankAccountById(UUID.fromString(debitTransaction.accountId));

        if(rootBankAccount == null)
            return null;

        BankAccount bankAccount = BankAccount.builder()
                .id(rootBankAccount.getId().toString())
                .status(rootBankAccount.getStatus())
                .balance(rootBankAccount.getBalance())
                .client(MockClient.getClientMock())
                .currency(BankAccount.Currency.valueOf(rootBankAccount.getCurrency()))
                .build();

        return bankAccount.debitFunds(debitTransaction);
    }
}
