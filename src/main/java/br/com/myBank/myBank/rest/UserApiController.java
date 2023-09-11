package br.com.myBank.myBank.rest;

import br.com.myBank.myBank.domain.user.User;
import br.com.myBank.myBank.service.user.UserService;
import jar.presentation.UserApi;
import jar.presentation.representation.CreatedUserResponse;
import jar.presentation.representation.UserRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class UserApiController implements UserApi {

    private ModelMapper modelMapper;
    private UserService service;

    @Override
    public ResponseEntity<CreatedUserResponse> createUser(UserRequest request) {
        log.info("### Start register person: {}", request);

        var user = service.saveUser(modelMapper.map(request, User.class));

        return ResponseEntity.ok(modelMapper.map(user, CreatedUserResponse.class));
    }
}
