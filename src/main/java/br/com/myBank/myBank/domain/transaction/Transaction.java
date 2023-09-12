package br.com.myBank.myBank.domain.transaction;

import br.com.myBank.myBank.domain.enums.EnumUserType;
import br.com.myBank.myBank.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payer;

    @ManyToOne
    @JoinColumn(name = "payee_id")
    private User payee;

    private BigDecimal value;

    public boolean checkPayerUserType() {
        return this.getPayer().getUserType().equals(EnumUserType.PESSOA_JURIDICA);
    }

    public boolean checkPayerBalance() {
        return (this.getPayer().getWallet().getBalance().compareTo(this.getValue())) < 0;
    }
}
