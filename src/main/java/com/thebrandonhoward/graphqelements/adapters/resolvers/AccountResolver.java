package com.thebrandonhoward.graphqelements.adapters.resolvers;

import com.thebrandonhoward.graphqelements.adapters.account.BankAccountLookupAdapter;
import com.thebrandonhoward.graphqelements.domain.models.account.AccountQuery;
import com.thebrandonhoward.graphqelements.domain.models.account.BankAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AccountResolver {
    private final BankAccountLookupAdapter bankAccountLookupAdapter;

    @EntityMapping
    public List<BankAccount> accounts(@Argument List<String> accountIds) {
        return accountIds.stream()
                .map(accountId -> bankAccountLookupAdapter.lookupAccount(UUID.fromString(accountId)))
                .toList();
    }
}
