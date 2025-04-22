package com.thebrandonhoward.graphqelements.domain.models.mocks;


import com.thebrandonhoward.graphqelements.domain.models.user.User;

public class MockUser {
    public static User createMockUser(String profileId) {
        User user = new User();
        user.userId = profileId;
        user.firstName = "John_" + profileId;
        user.middleName = "Smith_" + profileId;
        user.lastName = "Doe_" + profileId;
        return user;
    }
}
