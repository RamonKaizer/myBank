package br.com.myBank.myBank.service.user;

import br.com.myBank.myBank.domain.user.Account;
import br.com.myBank.myBank.domain.wallet.Wallet;
import br.com.myBank.myBank.exception.ErrorBadRequestException;
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

    public Account saveUser(Account account) {
        createSettingsUser(account);

        //return repository.save(account);
        return null;
    }

    private void createSettingsUser(Account account) {
        Wallet wallet = Wallet.builder()
                .accountNumber(String.valueOf(UUID.randomUUID()))
                .balance(BigDecimal.ZERO)
                .build();

        account.addWallet(wallet);
        account.checkUserType();
    }

    public Account getUserbyId(Long id) {
//        return repository.findById(id)
//                .orElseThrow(() -> new ErrorBadRequestException("User not found ID: " + id));

        return null;
    }

}
