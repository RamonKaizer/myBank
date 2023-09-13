package br.com.myBank.myBank.rest.transaction;

import br.com.myBank.myBank.service.transaction.TransactionService;
import jar.presentation.TransactionApi;
import jar.presentation.representation.TransactionRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class TransactionController implements TransactionApi {

    private TransactionService service;

    @Override
    public ResponseEntity<Void> makeTransfer(TransactionRequest request) {

        service.makeTransfer(request);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
