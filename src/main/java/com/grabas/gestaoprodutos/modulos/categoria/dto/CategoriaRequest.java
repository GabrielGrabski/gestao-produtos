package com.grabas.gestaoprodutos.modulos.categoria.dto;

import javax.validation.constraints.NotBlank;

public record CategoriaRequest(
        Integer id,

        @NotBlank(message = "Nome não pode ser vazio.")
        String nome,

        String descricao
) {
}
