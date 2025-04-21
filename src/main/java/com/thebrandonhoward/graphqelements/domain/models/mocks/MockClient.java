package com.thebrandonhoward.graphqelements.domain.models.mocks;

import com.thebrandonhoward.graphqelements.domain.models.account.Client;

import java.util.Random;
import java.util.UUID;

public class MockClient {
    public static Client getClientMock() {
        int rand  = new Random(System.currentTimeMillis()).nextInt();
        Client client = new Client();
        client.setId(UUID.randomUUID().toString());
        client.setFirstName("Tom_" + rand);
        client.setMiddleName("And_" + rand);
        client.setLastName("Jerry_" + rand);

        return client;
    }
}
