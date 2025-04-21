package com.thebrandonhoward.graphqelements.infrastructure.config.api.graphql.account;

import com.thebrandonhoward.graphqelements.domain.models.account.AccountQuery;
import com.thebrandonhoward.graphqelements.domain.models.account.BankAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Random;

@Controller
@Slf4j
public class AccountQueryController {
    @QueryMapping
    public AccountQuery accountQuery() {
        log.info("Getting account query");

        return new AccountQuery();
    }

    @SchemaMapping
    public List<BankAccount> accounts(AccountQuery query) {
        log.info("Getting all accounts");

        return List.of(
                new BankAccount(),
                new BankAccount(),
                new BankAccount(),
                new BankAccount());
    }

    @SchemaMapping
    public BankAccount accountById(@Argument String accountId, AccountQuery query) {
        log.info("Getting account query with id {}", accountId);

        List<BankAccount> accounts = accounts(query);

        return accounts.get(new Random().nextInt(3));
    }
}
