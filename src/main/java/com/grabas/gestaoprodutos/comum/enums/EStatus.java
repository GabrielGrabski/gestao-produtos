package com.grabas.gestaoprodutos.comum.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EStatus {

    A("ATIVO"),
    I("INATIVO");

    private final String descricao;
}
