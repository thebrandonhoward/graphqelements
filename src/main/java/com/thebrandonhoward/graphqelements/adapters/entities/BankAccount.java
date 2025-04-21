package com.thebrandonhoward.graphqelements.adapters.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "currency")
    String currency;

    @Column(name = "balance")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    BigDecimal balance;

    @Column(name = "status")
    @NonNull String status;
}