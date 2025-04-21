package com.thebrandonhoward.graphqelements.adapters.repositories;

import com.thebrandonhoward.graphqelements.adapters.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {
    BankAccount getBankAccountById(UUID id);
}