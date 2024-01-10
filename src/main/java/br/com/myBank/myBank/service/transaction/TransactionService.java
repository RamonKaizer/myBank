package br.com.myBank.myBank.service.transaction;

import br.com.myBank.myBank.domain.entity.Transaction;
import br.com.myBank.myBank.exception.ErrorBadRequestException;
import br.com.myBank.myBank.repository.transaction.TransactionRepository;
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
    private AuthorizationTransactionService authorizationTransactionService;

    @Transactional
    public void makeTransfer(TransactionRequest request) {

            Transaction transaction = createTransaction(request);

            transactionValidation(transaction);

            updateBalance(transaction);

            repository.save(transaction);
    }

    private Transaction createTransaction(TransactionRequest request) {
        return Transaction.builder()
                .payer(userService.getUserbyId(request.getPayer()))
                .payee(userService.getUserbyId(request.getPayee()))
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

        if(authorizationTransactionService.authorizeTransaction()) {
            throw new ErrorBadRequestException("Denied by external authorizer.");
        }
    }

    private void updateBalance(Transaction transaction) {
        transaction.getPayee().getWallet().addValue(transaction.getValue());
        transaction.getPayer().getWallet().removeValue(transaction.getValue());
    }
}
