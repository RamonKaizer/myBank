package br.com.myBank.myBank.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jar.presentation.UserApi;
import jar.presentation.representation.CreatedUserResponse;
import jar.presentation.representation.UserRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class UserApiController implements UserApi {

    private final Gson gson = new GsonBuilder().create();

    @Override
    public ResponseEntity<CreatedUserResponse> createUser(UserRequest userRequest) {
        log.info("### Iniciando serviço de cadastro de usuário com a request: {}", gson.toJson(userRequest));

        return null;
    }
}
