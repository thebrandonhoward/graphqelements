package com.thebrandonhoward.graphqelements.adapters;

import com.thebrandonhoward.graphqelements.domain.models.account.BankAccount;
import com.thebrandonhoward.graphqelements.domain.models.account.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class ClientResolver {

    @BatchMapping(typeName = "BankAccount", field = "client")  //Handles n + 1
    public Map<BankAccount, Client> clients(List<BankAccount> bankAccount) {
        log.info("Getting client");

        final Map<BankAccount, Client> clients = new HashMap<>();

        bankAccount.forEach(account -> {
            Client client = new Client();
            client.setId(account.getId());

            clients.put(account, client);
        });

        return clients;
    }

//    @SchemaMapping(typeName = "BankAccount", field = "client")
//    public Client client(BankAccount bankAccount) {
//        log.info("Getting client");
//
//        Client client = new Client();
//        client.setId(bankAccount.getId());
//
//        return client;
//    }
}
