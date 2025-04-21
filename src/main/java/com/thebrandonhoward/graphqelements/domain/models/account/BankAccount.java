package com.thebrandonhoward.graphqelements.domain.models.account;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class BankAccount {
    public enum Currency { CAD, EUR, USD }

    @NonNull String id = UUID.randomUUID().toString();
    Client client;
    @NonNull Currency currency = Currency.USD;
    @NonNull BigDecimal balance = BigDecimal.ZERO;
    @NonNull String status  = "ACTIVE";

    public static BankAccount createBankAccount(AccountApplication application) {
        return BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .balance(application.balance)
                .client(application.client)
                .currency(application.currency)
                .status("ACTIVE")
                .build();
    }

    public BankAccount creditFunds(@NonNull CreditTransaction creditTransaction) {
        this.balance = balance.add(applyConversionRate(creditTransaction.currency, creditTransaction.creditAmount));
        return this;
    }

    public BankAccount debitFunds(@NonNull DebitTransaction debitTransaction) {
        this.balance = balance.subtract(applyConversionRate(debitTransaction.currency, debitTransaction.debitAmount));
        return this;
    }

    private BigDecimal applyConversionRate(Currency currency, BigDecimal amount) {
        switch (currency) {
            case CAD: return amount;
            case EUR: return amount;
            case USD: return amount;
            default: return amount;
        }
    }
}
