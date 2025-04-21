package com.thebrandonhoward.graphqelements.domain.models.account;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class Client {
    @NonNull String id = UUID.randomUUID().toString();
    @NonNull String firstName = "";
    private String middleName;
    @NonNull String lastName = "";
}
