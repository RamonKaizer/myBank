package br.com.myBank.myBank.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallet")
public class Wallet {

    @Id
    private String accountNumber;

    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private BigDecimal balance;

    public void addValue(BigDecimal value) {
        this.balance = this.balance.add(value);
    }

    public void removeValue(BigDecimal value) {
        this.balance = this.balance.subtract(value);
    }
}
