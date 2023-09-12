package br.com.myBank.myBank.service.transaction;

import br.com.myBank.myBank.domain.transaction.Transaction;
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

    @Transactional
    public void makeTransfer(TransactionRequest request) {

        Transaction transaction = Transaction.builder()
                .payer(userService.getUserbyId(request.getPayer()))
                .payee(userService.getUserbyId(request.getPayee()))
                .value(request.getValue())
                .build();

        transferValidation(transaction);
    }

    public void transferValidation(Transaction transaction) {
        if(transaction.checkPayerUserType()) {
            throw new RuntimeException("It's not possible to send amounts from a merchant user!");
        }

        if(transaction.checkPayerBalance()) {
            throw new RuntimeException("Payer don't have balance for make this transfer!");
        }
    }
}
