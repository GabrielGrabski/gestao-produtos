package com.grabas.gestaoprodutos.modulos.produto.repository;

import com.grabas.gestaoprodutos.modulos.categoria.model.Categoria;
import com.grabas.gestaoprodutos.modulos.produto.model.Produto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Categoria> {
}
