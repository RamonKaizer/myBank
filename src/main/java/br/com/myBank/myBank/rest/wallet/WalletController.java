package br.com.myBank.myBank.rest.wallet;

import br.com.myBank.myBank.service.wallet.WalletService;
import jar.presentation.WalletApi;
import jar.presentation.representation.Deposit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class WalletController implements WalletApi {

    @Autowired
    private WalletService service;

    @Override
    public ResponseEntity<Void> makeDeposit(Deposit request) {
        service.deposit(request);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
