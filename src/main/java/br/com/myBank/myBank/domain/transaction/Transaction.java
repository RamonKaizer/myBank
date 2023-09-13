package br.com.myBank.myBank.domain.transaction;

import br.com.myBank.myBank.domain.enums.EnumUserType;
import br.com.myBank.myBank.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@EqualsAndHashCode(of = "transactionalId")
@ToString(of = "transactionalId")
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionalId;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payer;

    @ManyToOne
    @JoinColumn(name = "payee_id")
    private User payee;

    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private BigDecimal value;

    @Column(nullable = false)
    private LocalDateTime transactionTime;

    @PrePersist
    protected void onCreate() {
        this.transactionTime = LocalDateTime.now();
    }

    public boolean checkPayerUserType() {
        return this.getPayer().getUserType().equals(EnumUserType.PESSOA_JURIDICA);
    }

    public boolean checkPayerBalance() {
        return (this.getPayer().getWallet().getBalance().compareTo(this.getValue())) < 0;
    }
}
