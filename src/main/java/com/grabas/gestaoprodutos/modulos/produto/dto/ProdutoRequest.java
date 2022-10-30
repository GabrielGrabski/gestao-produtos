package com.grabas.gestaoprodutos.modulos.produto.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public record ProdutoRequest(@NotNull String nome,
                             String descricao,
                             List<Integer> categoriasIds) {
}
