package com.fitness.activityService.client;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import com.fitness.activityService.exception.UserServiceUnavailableException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserServiceClient {

    private static final String UNAVAILABLE_MESSAGE = "User service is currently unavailable";

    private final RestClient userServiceRestClient;

    public boolean userExists(Long userId) {
        try {
            Boolean exists = userServiceRestClient.get()
                    .uri("/api/v1/users/{userId}/validate", userId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                        throw new UserServiceUnavailableException(UNAVAILABLE_MESSAGE);
                    })
                    .body(Boolean.class);
            return Boolean.TRUE.equals(exists);
        } catch (HttpClientErrorException.NotFound ex) {
            return false;
        } catch (RestClientException ex) {
            // Covers connection failures and timeouts (ResourceAccessException) and unexpected responses
            throw new UserServiceUnavailableException(UNAVAILABLE_MESSAGE, ex);
        }
    }
}
