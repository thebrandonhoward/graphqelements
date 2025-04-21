package com.thebrandonhoward.graphqelements.adapters.account;

import com.thebrandonhoward.graphqelements.adapters.repositories.BankAccountRepository;
import com.thebrandonhoward.graphqelements.domain.models.account.BankAccount;
import com.thebrandonhoward.graphqelements.domain.models.account.CreditTransaction;
import com.thebrandonhoward.graphqelements.domain.models.mocks.MockClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class BankAccountCreditAdapter {
    private final  BankAccountRepository bankAccountRepository;

    public BankAccount creditBankAccount(CreditTransaction creditTransaction) {
        com.thebrandonhoward.graphqelements.adapters.entities.BankAccount rootBankAccount
                = bankAccountRepository.getBankAccountById(UUID.fromString(creditTransaction.accountId));

        if(rootBankAccount == null)
            return null;

        BankAccount bankAccount = BankAccount.builder()
                .id(rootBankAccount.getId().toString())
                .status(rootBankAccount.getStatus())
                .balance(rootBankAccount.getBalance())
                .client(MockClient.getClientMock())
                .currency(BankAccount.Currency.valueOf(rootBankAccount.getCurrency()))
                .build();

        bankAccount.creditFunds(creditTransaction);

        rootBankAccount.setBalance(bankAccount.getBalance());

        bankAccountRepository.save(rootBankAccount);

        return bankAccount;
    }
}
