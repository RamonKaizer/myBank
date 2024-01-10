package br.com.myBank.myBank.domain.entity;

import br.com.myBank.myBank.domain.enums.EnumUserType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(of = "accountId")
@ToString(of = "accountId")
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String cpfCnpj;

    @Enumerated(value = EnumType.STRING)
    private EnumUserType userType;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Wallet wallet;

    @OneToMany(mappedBy = "payer")
    private List<Transaction> transactionAsPayer = new ArrayList<>();

    @OneToMany(mappedBy = "payee")
    private List<Transaction> transactionAsPayee = new ArrayList<>();

    public void addWallet(Wallet wallet){
        this.wallet = wallet;
    }

    public void checkUserType() {
        long LENGTH_CPF = 11;

        if(this.cpfCnpj.length() == LENGTH_CPF) {
            this.setUserType(EnumUserType.PESSOA_FISICA);
        } else {
            this.setUserType(EnumUserType.PESSOA_JURIDICA);
        }
    }
}
