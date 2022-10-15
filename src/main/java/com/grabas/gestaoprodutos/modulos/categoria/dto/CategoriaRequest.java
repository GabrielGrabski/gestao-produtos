package com.grabas.gestaoprodutos.modulos.categoria.dto;

import javax.validation.constraints.NotNull;

public record CategoriaRequest(@NotNull String nome, String descricao) {
}
