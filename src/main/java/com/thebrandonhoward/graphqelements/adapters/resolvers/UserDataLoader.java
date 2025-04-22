package com.thebrandonhoward.graphqelements.adapters.resolvers;

//import com.netflix.graphql.dgs.DgsDataLoader;
import com.thebrandonhoward.graphqelements.adapters.account.UserLoaderAdapter;
import com.thebrandonhoward.graphqelements.domain.models.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.MappedBatchLoader;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

//@DgsDataLoader(name = "user")
@RequiredArgsConstructor
@Slf4j
public class UserDataLoader implements MappedBatchLoader<String, User> {
    private final UserLoaderAdapter userLoaderAdapter;

    @Override
    public CompletionStage<Map<String, User>> load(Set<String> set) {
        log.info("Loading users from dataloader: {}", set);

        return CompletableFuture.supplyAsync(() -> userLoaderAdapter.loadUsers(set));
    }
}
