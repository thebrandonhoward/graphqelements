package com.thebrandonhoward.graphqelements.domain.models.user;

import lombok.Data;

@Data
public class User {
    public String userId;
    public String firstName;
    public String middleName;
    public String lastName;
}
