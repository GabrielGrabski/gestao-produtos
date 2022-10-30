package com.grabas.gestaoprodutos.modulos.produto.dto;

import com.grabas.gestaoprodutos.comum.enums.EStatus;
import com.grabas.gestaoprodutos.modulos.categoria.dto.CategoriaSemProdutosResponse;

import java.util.List;

public record ProdutoResponse(Integer id,
                              String nome,
                              String descricao,
                              EStatus status,
                              List<CategoriaSemProdutosResponse> categorias) {
}
