package br.com.myBank.myBank.repository.wallet;

import br.com.myBank.myBank.domain.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository  extends JpaRepository<Wallet, Long> {
}
