package br.com.myBank.myBank.domain.enums;

import lombok.Getter;

@Getter
public enum EnumUserType {
    PESSOA_FISICA(1),
    PESSOA_JURIDICA(2);

    private final int code;

    EnumUserType(int code) {
        this.code = code;
    }

}
