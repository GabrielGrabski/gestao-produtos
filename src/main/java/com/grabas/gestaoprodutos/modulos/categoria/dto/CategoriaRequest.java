package com.grabas.gestaoprodutos.modulos.categoria.dto;

import com.grabas.gestaoprodutos.comum.enums.EStatus;

import javax.validation.constraints.NotBlank;

public record CategoriaRequest(
        Integer id,

        @NotBlank(message = "Nome não pode ser vazio.")
        String nome,

        EStatus status,

        String descricao
) {
}
