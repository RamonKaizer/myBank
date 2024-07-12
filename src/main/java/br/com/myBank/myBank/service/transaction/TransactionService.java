package br.com.myBank.myBank.service.transaction;

import br.com.myBank.myBank.domain.entity.Account;
import br.com.myBank.myBank.domain.entity.Transaction;
import br.com.myBank.myBank.domain.entity.User;
import br.com.myBank.myBank.exception.ErrorBadRequestException;
import br.com.myBank.myBank.repository.transaction.TransactionRepository;
import br.com.myBank.myBank.service.account.AccountService;
import br.com.myBank.myBank.service.user.UserService;
import jakarta.transaction.Transactional;
import jar.presentation.representation.TransactionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorizationTransactionService authorizationTransactionService;

    @Transactional
    public void makeTransfer(TransactionRequest request) {

        User user = userService.getUserLogin();

        Transaction transaction = createTransaction(request, user.getAccount());

        transactionValidation(transaction);

        updateBalance(transaction);

        repository.save(transaction);
    }

    private Transaction createTransaction(TransactionRequest request, Account payee) {
        return Transaction.builder()
                .payer(payee)
                .payee(accountService.getAccountbyId(request.getPayee()))
                .value(request.getValue())
                .build();
    }

    private void transactionValidation(Transaction transaction) {
        if(transaction.checkPayerUserType()) {
            throw new ErrorBadRequestException("It's not possible to send amounts from a merchant user!");
        }

        if(transaction.checkPayerBalance()) {
            throw new ErrorBadRequestException("Payer don't have balance for make this transfer!");
        }

        if(!authorizationTransactionService.authorizeTransaction()) {
            throw new ErrorBadRequestException("Denied by external authorizer.");
        }
    }

    private void updateBalance(Transaction transaction) {
        transaction.getPayee().getWallet().addValue(transaction.getValue());
        transaction.getPayer().getWallet().removeValue(transaction.getValue());
    }
}
