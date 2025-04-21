package com.thebrandonhoward.graphqelements.infrastructure.config.interceptors;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Objects;

@Component
public class RequestHeaderInterceptor implements WebGraphQlInterceptor {
    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        //Grabs a random header and adds it to the Graphql Context
        String value = request.getHeaders().getFirst("token");

        if(Objects.nonNull(value)) {
            request.configureExecutionInput((executionInput, builder) -> {
                return builder.graphQLContext(Collections.singletonMap("token", value)).build();
            });
        }

        return chain.next(request);
    }
}
