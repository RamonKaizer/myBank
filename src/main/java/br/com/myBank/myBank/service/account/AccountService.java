package br.com.myBank.myBank.service.account;

import br.com.myBank.myBank.domain.entity.Account;
import br.com.myBank.myBank.exception.ErrorBadRequestException;
import br.com.myBank.myBank.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account getAccountbyId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ErrorBadRequestException("Account not found ID: " + id));
    }
}
