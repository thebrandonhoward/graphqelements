package com.thebrandonhoward.graphqelements.infrastructure.config.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@Slf4j
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        AtomicInteger counter = new AtomicInteger();

        List<UserDetails> list1 = Stream.generate(() -> {
                                counter.incrementAndGet();
                                return User.withDefaultPasswordEncoder()
                                    .username("user"+counter)
                                    .password("password")
                                    .roles("USER","APP_RW")
                                    .build();
                            })
                            .limit(5)
                            .toList();

        List<UserDetails> list2 = Stream.generate(() -> {
                    counter.incrementAndGet();
                    return User.withDefaultPasswordEncoder()
                            .username("user"+counter)
                            .password("password")
                            .roles("USER","ADMIN")
                            .build();
                })
                .limit(5)
                .toList();

        List<UserDetails> list3 = new ArrayList<>(list1);
        list3.addAll(list2);

        log.info("Found {} users", list3.size());
        log.info(list3.toString());

        return new InMemoryUserDetailsManager(list3);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
//                                .requestMatchers("/graphql").permitAll()
                                .requestMatchers("/graphiql").permitAll()
//                                .requestMatchers(new MvcRequestMatcher.Builder(introspector).servletPath("/graphiql").pattern("/**")).permitAll()
//                                .requestMatchers(new MvcRequestMatcher.Builder(introspector).pattern("/graphql")).permitAll()
                                .anyRequest()
                                .authenticated() )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
                .httpBasic(Customizer.withDefaults() );

        return httpSecurity.build();
    }
}
