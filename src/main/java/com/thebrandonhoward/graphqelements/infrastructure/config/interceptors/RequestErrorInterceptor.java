package com.thebrandonhoward.graphqelements.infrastructure.config.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Component
@Slf4j
public class RequestErrorInterceptor implements WebGraphQlInterceptor {
    @Override
    public @NotNull Mono<WebGraphQlResponse> intercept(@NotNull WebGraphQlRequest request, Chain chain) {
        return chain.next(request)
                .map(this::processResponse);
    }

    private WebGraphQlResponse processResponse(WebGraphQlResponse response) {
        if(response.isValid()) {
            log.info("Processed valid response: {}", response);
            return response;
        }
        else {
            log.error("Error processing request: {}", response);
            //I could add more in depth handling or alter before sending back
            return response.transform(builder -> builder.errors(new ArrayList<>()).build());
        }
    }
}
