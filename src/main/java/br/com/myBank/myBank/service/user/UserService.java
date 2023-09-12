package br.com.myBank.myBank.service.user;

import br.com.myBank.myBank.domain.user.User;
import br.com.myBank.myBank.domain.wallet.Wallet;
import br.com.myBank.myBank.exception.ErroException;
import br.com.myBank.myBank.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository repository;

    public User saveUser(User user) {
        createSettingsUser(user);

        try {
            return repository.save(user);
        } catch (RuntimeException e) {
            throw new ErroException("error saving user", e);
        }
    }

    private void createSettingsUser(User user) {
        Wallet wallet = Wallet.builder()
                .accountNumber(String.valueOf(UUID.randomUUID()))
                .balance(BigDecimal.ZERO)
                .build();

        user.addWallet(wallet);
        user.checkUserType();
    }

    public User getUserbyId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ErroException("User não encontrado com o ID: " + id));

    }

}
