package com.grabas.gestaoprodutos.modulos.categoria.dto;

import com.grabas.gestaoprodutos.comum.enums.EStatus;
import com.grabas.gestaoprodutos.modulos.produto.dto.ProdutoSemCategoriaResponse;
import com.grabas.gestaoprodutos.modulos.produto.model.Produto;

import java.util.List;

public record CategoriaResponse(Integer id, String nome, EStatus status, String descricao, List<ProdutoSemCategoriaResponse> produtos) {
}
