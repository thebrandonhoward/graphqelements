package com.thebrandonhoward.graphqelements.domain.models.mocks;

import com.thebrandonhoward.graphqelements.domain.models.account.*;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

public class MockBankAccount {
    @NonNull
    String id = UUID.randomUUID().toString();
    Client client;
    @NonNull
    com.thebrandonhoward.graphqelements.domain.models.account.BankAccount.Currency currency = com.thebrandonhoward.graphqelements.domain.models.account.BankAccount.Currency.USD;
    @NonNull
    BigDecimal balance = BigDecimal.ZERO;
    @NonNull
    String status = "ACTIVE";

    public static com.thebrandonhoward.graphqelements.domain.models.account.BankAccount getBankAccountMock() {
        return com.thebrandonhoward.graphqelements.domain.models.account.BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .balance(BigDecimal.valueOf(new Random().nextDouble()))
                .client(MockClient.getClientMock())
                .status("ACTIVE")
                .build();
    }

}
