package br.com.myBank.myBank.domain.user;

import br.com.myBank.myBank.domain.enums.EnumUserType;
import br.com.myBank.myBank.domain.wallet.Wallet;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(of = "userId")
@ToString(of = "userId")
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String cpfCnpj;

    @Enumerated(value = EnumType.STRING)
    private EnumUserType userType;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Wallet wallet;

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
