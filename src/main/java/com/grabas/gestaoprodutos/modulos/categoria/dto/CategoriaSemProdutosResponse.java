package com.grabas.gestaoprodutos.modulos.categoria.dto;

import com.grabas.gestaoprodutos.comum.enums.EStatus;

public record CategoriaSemProdutosResponse(Integer id, String nome, EStatus status, String descricao) {
}
