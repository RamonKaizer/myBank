package br.com.myBank.myBank.domain.entity;

import br.com.myBank.myBank.domain.enums.EnumUserType;
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
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private Account payer;

    @ManyToOne
    @JoinColumn(name = "payee_id")
    private Account payee;

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
