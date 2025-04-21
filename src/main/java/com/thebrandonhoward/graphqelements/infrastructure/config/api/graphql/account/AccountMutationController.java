package com.thebrandonhoward.graphqelements.infrastructure.config.api.graphql.account;

import com.thebrandonhoward.graphqelements.domain.models.account.AccountMutation;
import com.thebrandonhoward.graphqelements.domain.models.account.BankAccount;
import com.thebrandonhoward.graphqelements.domain.models.account.CreditTransaction;
import com.thebrandonhoward.graphqelements.domain.models.account.DebitTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class AccountMutationController {
    @MutationMapping
    public AccountMutation accountMutation() {
        log.info("Account mutation");
        return new AccountMutation();
    }

    @SchemaMapping
    public BankAccount creditAccount(@Argument CreditTransaction creditTransaction, AccountMutation accountMutation) {
        log.info("Credit transaction: {}", creditTransaction);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setStatus("SUCCESS");

        return bankAccount;
    }

    @SchemaMapping
    public BankAccount debitAccount(@Argument DebitTransaction debitTransaction, AccountMutation accountMutation) {
        log.info("Debit transaction: {}", debitTransaction);


        BankAccount bankAccount = new BankAccount();
        bankAccount.setStatus("SUCCESS");

        return bankAccount;
    }
}
