package br.com.myBank.myBank.domain.enums;

import lombok.Getter;

@Getter
public enum EnumPersonType {
    SHOPKEEPER(1),
    USER(2);

    private final int code;

    EnumPersonType(int code) {
        this.code = code;
    }

}
