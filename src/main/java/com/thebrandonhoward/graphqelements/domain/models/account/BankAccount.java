package com.thebrandonhoward.graphqelements.domain.models.account;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class BankAccount {
    public enum Currency { CAD, EUR, USD }

    @NonNull String id = UUID.randomUUID().toString();
    Client client;
    @NonNull Currency currency = Currency.USD;
    @NonNull BigDecimal balance = BigDecimal.ZERO;
    @NonNull String status  = "ACTIVE";
}
