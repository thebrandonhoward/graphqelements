package com.thebrandonhoward.graphqelements.adapters.account;

import com.thebrandonhoward.graphqelements.domain.models.mocks.MockUser;
import com.thebrandonhoward.graphqelements.domain.models.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserLoaderAdapter {
    public Map<String, User> loadUsers(Set<String> profileIds) {
        return profileIds.stream()
                .collect(Collectors.toMap(Function.identity(), MockUser::createMockUser));
    }
}
