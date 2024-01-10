package br.com.myBank.myBank.rest.user;

import br.com.myBank.myBank.config.security.TokenService;
import br.com.myBank.myBank.domain.entity.User;
import br.com.myBank.myBank.service.user.UserService;
import jar.presentation.UserApi;
import jar.presentation.representation.LoginRequest;
import jar.presentation.representation.TokenResponse;
import jar.presentation.representation.UserRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class UserApiController implements UserApi {

    private final ModelMapper modelMapper;

    private UserService service;
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<Void> createUser(UserRequest request) {
        log.info("### Start register person: {}", request);

        service.saveUser(request);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<TokenResponse> login(LoginRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        TokenResponse tokenResponse = new TokenResponse();

        tokenResponse.setToken(token);

        return ResponseEntity.ok(tokenResponse);
    }
}
