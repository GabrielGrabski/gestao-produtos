package com.grabas.gestaoprodutos.modulos.produto.dto;

import com.grabas.gestaoprodutos.comum.enums.EStatus;

public record ProdutoSemCategoriaResponse(Integer id,
                                          String nome,
                                          EStatus status,
                                          String descricao) {
}
