package br.com.myBank.myBank.service.transaction;

import br.com.myBank.myBank.exception.ErrorBadGatewayException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class AuthorizationTransactionService {

    private final RestTemplate restTemplate;

    private final String authorizationURL;

    public AuthorizationTransactionService(RestTemplate restTemplate, @Value("${hosts.authorization}") String authorizationURL ){
        this.restTemplate = restTemplate;
        this.authorizationURL = authorizationURL;
    }

    public boolean authorizeTransaction() {
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(authorizationURL, Map.class);

            return Objects.requireNonNull(response.getBody()).get("message").equals("Autorizado");

        }catch(Exception e) {
            throw new ErrorBadGatewayException("Error calling external transaction authorizer.");
        }
    }
}
