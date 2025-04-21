package com.thebrandonhoward.graphqelements.infrastructure.config.api.graphql.account;

import com.thebrandonhoward.graphqelements.adapters.account.BankAccountCreditAdapter;
import com.thebrandonhoward.graphqelements.adapters.account.BankAccountDebitAdapter;
import com.thebrandonhoward.graphqelements.adapters.account.CreateBankAccountAdapter;
import com.thebrandonhoward.graphqelements.domain.models.account.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AccountMutationController {
    private final CreateBankAccountAdapter createBankAccountAdapter;
    private final BankAccountCreditAdapter bankAccountCreditAdapter;
    private final BankAccountDebitAdapter bankAccountDebitAdapter;

    @MutationMapping
    public AccountMutation accountMutation() {
        log.info("Account mutation");
        return new AccountMutation();
    }

    @SchemaMapping
    public BankAccount createAccount(@Argument AccountApplication accountApplication, AccountMutation accountMutation) {
        log.info("Create account");

        return createBankAccountAdapter.createBankAccount(accountApplication);
    }

    @SchemaMapping
    public BankAccount creditAccount(@Argument CreditTransaction creditTransaction, AccountMutation accountMutation) {
        log.info("Credit transaction: {}", creditTransaction);

        return bankAccountCreditAdapter.creditBankAccount(creditTransaction);
    }

    @SchemaMapping
    public BankAccount debitAccount(@Argument DebitTransaction debitTransaction, AccountMutation accountMutation) {
        log.info("Debit transaction: {}", debitTransaction);

        return bankAccountDebitAdapter.debitBankAccount(debitTransaction);
    }
}
