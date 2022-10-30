package com.grabas.gestaoprodutos.utils;

import com.grabas.gestaoprodutos.comum.enums.EStatus;
import com.grabas.gestaoprodutos.modulos.categoria.dto.CategoriaRequest;
import com.grabas.gestaoprodutos.modulos.categoria.dto.CategoriaSemProdutosResponse;
import com.grabas.gestaoprodutos.modulos.categoria.model.Categoria;
import com.grabas.gestaoprodutos.modulos.produto.dto.ProdutoResponse;
import com.grabas.gestaoprodutos.modulos.produto.dto.ProdutoSemCategoriaResponse;
import com.grabas.gestaoprodutos.modulos.produto.model.Produto;

import java.util.List;

public class TestUtils {

    public static Categoria umaCategoria() {
        return Categoria.builder()
                .id(1)
                .nome("Categoria Teste")
                .status(EStatus.A)
                .descricao("Descrição Teste")
                .build();
    }

    public static CategoriaRequest umaCategoriaRequest(Categoria categoria) {
        return new CategoriaRequest(categoria.getNome(), categoria.getDescricao());
    }

    public static Categoria umaCategoriaSemNome() {
        return Categoria.builder()
                .id(1)
                .nome(null)
                .status(EStatus.A)
                .descricao("Descrição Teste")
                .build();
    }

    public static CategoriaRequest umaCategoriaRequestSemNome(Categoria categoria) {
        return new CategoriaRequest(null, categoria.getDescricao());
    }

    public static ProdutoSemCategoriaResponse umProdutoSemCategoriaResponse(Produto produto) {
        return new ProdutoSemCategoriaResponse(
                produto.getId(),
                produto.getNome(),
                produto.getStatus(),
                produto.getDescricao()
        );
    }

    public static CategoriaSemProdutosResponse umaCategoriaSemProdutosResponse(Categoria categoria) {
        return new CategoriaSemProdutosResponse(
                null,
                categoria.getNome(),
                categoria.getStatus(),
                categoria.getDescricao());
    }

    public static ProdutoResponse umProdutoResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getStatus(),
                List.of(umaCategoriaSemProdutosResponse(umaCategoria()))
        );
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
