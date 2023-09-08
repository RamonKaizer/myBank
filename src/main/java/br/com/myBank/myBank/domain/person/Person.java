package br.com.myBank.myBank.domain.person;

import br.com.myBank.myBank.domain.enums.EnumPersonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String cpf;
    private BigDecimal wallet;
    private EnumPersonType personType;
    private LocalDate birth;
}
