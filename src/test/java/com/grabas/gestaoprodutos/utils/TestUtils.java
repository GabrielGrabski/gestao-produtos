package com.grabas.gestaoprodutos.utils;

import com.grabas.gestaoprodutos.modulos.categoria.dto.CategoriaRequest;
import com.grabas.gestaoprodutos.modulos.categoria.dto.CategoriaResponse;
import com.grabas.gestaoprodutos.modulos.categoria.model.Categoria;
import com.grabas.gestaoprodutos.modulos.produto.model.Produto;

import java.util.List;

public class TestUtils {

    public static Categoria umaCategoria() {
        return Categoria.builder()
                .id(1)
                .nome("Categoria Teste")
                .descricao("Descrição Teste")
                .build();
    }

    public static CategoriaRequest umaCategoriaRequest(Categoria categoria) {
        return new CategoriaRequest(categoria.getNome(), categoria.getDescricao());
    }

    public static CategoriaResponse umaCategoriaResponse(Categoria categoria) {
        return new CategoriaResponse(categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.getProdutos());
    }

    public static Produto umProduto() {
        return Produto.builder()
                .id(1)
                .nome("Produto Teste")
                .descricao("Descrição Produto")
                .categorias(List.of(umaCategoria()))
                .build();
    }
}
