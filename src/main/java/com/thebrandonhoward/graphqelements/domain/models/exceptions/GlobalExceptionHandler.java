package com.thebrandonhoward.graphqelements.domain.models.exceptions;

import org.springframework.graphql.execution.ErrorType;
import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import lombok.NonNull;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.Instant;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @GraphQlExceptionHandler
    public GraphQLError handle(@NonNull BankAccountNotFoundException bankAccountNotFoundException, @NonNull DataFetchingEnvironment dataFetchingEnvironment) {
        //Additional Info
        Map<String, Object> errorMapExt = Map.of(
                "errorCode", "ACCOUNT_NOT_FOUND",
                "userMessage","The account ID does not exist.",
                "timestamp", Instant.now().toString(),
                "possibleNextSteps", "Confirm with senior leadership and try again." );

        //Global UI Friendly Response
        return GraphQLError.newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message("Account not found. Please try again. " + bankAccountNotFoundException.getMessage())
                .path(dataFetchingEnvironment.getExecutionStepInfo().getPath())
                .location(dataFetchingEnvironment.getField().getSourceLocation())
                .extensions(errorMapExt)
                .build();
    }
}
