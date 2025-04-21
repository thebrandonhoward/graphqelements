package com.thebrandonhoward.graphqelements.adapters.account;

import com.thebrandonhoward.graphqelements.adapters.repositories.BankAccountRepository;
import com.thebrandonhoward.graphqelements.domain.models.account.BankAccount;
import com.thebrandonhoward.graphqelements.domain.models.mocks.MockClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class BankAccountLookupAdapter {
    private final BankAccountRepository bankAccountRepository;

    public BankAccount lookupAccount(UUID accountId) {
        com.thebrandonhoward.graphqelements.adapters.entities.BankAccount rootBankAccount
                = bankAccountRepository.getBankAccountById(accountId);

        if(Objects.isNull(rootBankAccount))
            return null;

        return BankAccount.builder()
                .id(rootBankAccount.getId().toString())
                .status(rootBankAccount.getStatus())
                .balance(rootBankAccount.getBalance())
                .client(MockClient.getClientMock())
                .currency(BankAccount.Currency.valueOf(rootBankAccount.getCurrency()))
                .build();
    }
}
