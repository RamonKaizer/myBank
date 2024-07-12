package br.com.myBank.myBank.repository.account;

import br.com.myBank.myBank.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
