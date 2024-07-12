package br.com.myBank.myBank.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallets")
public class Wallet {

    private static final AtomicInteger counter = new AtomicInteger(0);

    @Id
    @Column(length = 6)
    private String accountNumber;

    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private BigDecimal balance;

    @PrePersist
    public void generateAccountNumber() {
        this.accountNumber = String.format("%06d", counter.incrementAndGet());
    }

    public void addValue(BigDecimal value) {
        this.balance = this.balance.add(value);
    }

    public void removeValue(BigDecimal value) {
        this.balance = this.balance.subtract(value);
    }
}
