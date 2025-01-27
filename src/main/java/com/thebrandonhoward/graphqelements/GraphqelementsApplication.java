package com.thebrandonhoward.graphqelements;

import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Subscription;
import jakarta.annotation.PostConstruct;
import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class GraphqelementsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqelementsApplication.class, args);
    }

//    @PostConstruct
//    void init() {
//        WebClient wc = WebClient.create("https://echo.websocket.org/.sse ");
//        HttpGraphQlClient httpGraphQlClient = HttpGraphQlClient.create(wc);
//    }
}
