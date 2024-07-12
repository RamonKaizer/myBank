package br.com.myBank.myBank.service.wallet;

import br.com.myBank.myBank.domain.entity.Wallet;
import br.com.myBank.myBank.repository.wallet.WalletRepository;
import br.com.myBank.myBank.service.user.UserService;
import jakarta.transaction.Transactional;
import jar.presentation.representation.Deposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private WalletRepository repository;

    @Autowired
    private UserService userService;

    @Transactional
    public void deposit(Deposit request) {
        Wallet wallet = userService.getUserLogin().getAccount().getWallet();

        wallet.addValue(request.getValue());

        repository.save(wallet);
    }
}
