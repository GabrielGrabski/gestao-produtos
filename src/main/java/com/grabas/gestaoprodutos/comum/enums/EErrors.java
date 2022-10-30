package com.grabas.gestaoprodutos.comum.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EErrors {

    CATEGORIA_NAO_ENCONTRADA("Categoria não encontrada."),
    CATEGORIA_COM_PRODUTOS("A categoria possui produtos associados.");


    private final String descricao;
}
