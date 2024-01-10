package br.com.myBank.myBank.service.user;

import br.com.myBank.myBank.domain.enums.UserRole;
import br.com.myBank.myBank.domain.entity.Account;
import br.com.myBank.myBank.domain.entity.User;
import br.com.myBank.myBank.domain.entity.Wallet;
import br.com.myBank.myBank.repository.user.UserRepository;
import jar.presentation.representation.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void saveUser(UserRequest request) {
        User user = modelMapper.map(request, User.class);

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());

        user.setPassword(encryptedPassword);

        Account account = createSettingsUser(request);

        user.setAccount(account);
        user.setRole(UserRole.USER);

        repository.save(user);
    }

    private Account createSettingsUser(UserRequest request) {
        Account account = new Account();

        account.setFullName(request.getFullName());
        account.setCpfCnpj(request.getCpfCnpj());

        Wallet wallet = Wallet.builder()
                .accountNumber(String.valueOf(UUID.randomUUID()))
                .balance(BigDecimal.ZERO)
                .build();

        account.addWallet(wallet);
        account.checkUserType();

        return account;
    }

    public Account getUserbyId(Long id) {
//        return repository.findById(id)
//                .orElseThrow(() -> new ErrorBadRequestException("User not found ID: " + id));

        return null;
    }

}
