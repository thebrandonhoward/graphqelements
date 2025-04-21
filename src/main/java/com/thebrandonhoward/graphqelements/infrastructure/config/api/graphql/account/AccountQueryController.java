package com.thebrandonhoward.graphqelements.infrastructure.config.api.graphql.account;

import com.thebrandonhoward.graphqelements.adapters.account.BankAccountLookupAdapter;
import com.thebrandonhoward.graphqelements.adapters.repositories.BankAccountRepository;
import com.thebrandonhoward.graphqelements.domain.models.account.AccountQuery;
import com.thebrandonhoward.graphqelements.domain.models.account.BankAccount;
import com.thebrandonhoward.graphqelements.domain.models.mocks.MockBankAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AccountQueryController {
    private final BankAccountLookupAdapter bankAccountLookupAdapter;

    @QueryMapping
    public AccountQuery accountQuery() {
        log.info("Getting account query");

        return new AccountQuery();
    }

    @SchemaMapping
    public List<BankAccount> accounts(AccountQuery query) {
        log.info("Getting all accounts");

        return List.of(MockBankAccount.getBankAccountMock(),
                        MockBankAccount.getBankAccountMock());
    }

    @SchemaMapping
    public BankAccount accountById(@Argument String accountId, AccountQuery query) {
        log.info("Getting account query with id {}", accountId);

        return bankAccountLookupAdapter.lookupAccount(UUID.fromString(accountId));
    }
}
