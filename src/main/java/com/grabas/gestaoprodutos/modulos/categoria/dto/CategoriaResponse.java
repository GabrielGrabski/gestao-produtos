package com.grabas.gestaoprodutos.modulos.categoria.dto;

import com.grabas.gestaoprodutos.modulos.produto.model.Produto;

import java.util.List;

public record CategoriaResponse(Integer id, String nome, String descricao, List<Produto> produtos) {
}
