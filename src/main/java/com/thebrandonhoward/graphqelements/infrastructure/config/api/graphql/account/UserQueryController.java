package com.thebrandonhoward.graphqelements.infrastructure.config.api.graphql.account;

//import com.netflix.graphql.dgs.*;

import com.thebrandonhoward.graphqelements.adapters.resolvers.UserDataLoader;
import com.thebrandonhoward.graphqelements.domain.models.user.User;
import com.thebrandonhoward.graphqelements.domain.models.user.UserProfile;
import com.thebrandonhoward.graphqelements.domain.models.user.UserQuery;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

//@DgsComponent
@Controller
@Slf4j
public class UserQueryController {
    @QueryMapping
    public UserQuery userQuery() {
        log.info("Getting user query");
        return new UserQuery();
    }

    @SchemaMapping(typeName = "UserQuery", field = "userProfileById")
    public UserProfile userProfileById(@Argument String userId,
                                       UserQuery userQuery, DataFetchingEnvironment dataFetchingEnvironment) {
        log.info("Getting user profile by query {}", userQuery);

        UserProfile userProfile = new UserProfile();
        userProfile.setProfileId(UUID.randomUUID().toString());

        return userProfile;
    }

    @SchemaMapping(typeName = "UserProfile", field = "user")
    public CompletableFuture<User> user(UserProfile userProfile, DataFetchingEnvironment dataFetchingEnvironment) {
        log.info("Getting user by query {}", userProfile);
        DataLoader<String, User> usrLoader = dataFetchingEnvironment.getDataLoader("UserDataLoader");

        User user = dataFetchingEnvironment.getSource();

        return usrLoader.load(user.userId);
    }

//    @DgsQuery
//    public UserQuery userQuery() {
//        log.info("Getting user query");
//        return new UserQuery();
//    }
//
//    @DgsData(parentType = "UserQuery", field = "userProfileById")
//    public UserProfile userProfileById(@InputArgument String userId, UserQuery userQuery, DgsDataFetchingEnvironment dgsDataFetchingEnvironment) {
//        log.info("Getting user profile by query {}", userQuery);
//
//        UserProfile userProfile = new UserProfile();
//        userProfile.setProfileId(UUID.randomUUID().toString());
//
//        return userProfile;
//    }
//
//    @DgsData(parentType = "UserProfile", field = "user")
//    public CompletableFuture<User> user(UserProfile userProfile, DgsDataFetchingEnvironment dgsDataFetchingEnvironment) {
//        log.info("Getting user by query {}", userProfile);
//        DataLoader<String, User> usrLoader = dgsDataFetchingEnvironment.getDataLoader(UserDataLoader.class);
//
//        User user = dgsDataFetchingEnvironment.getSource();
//
//        return usrLoader.load(user.userId);
//    }
}
