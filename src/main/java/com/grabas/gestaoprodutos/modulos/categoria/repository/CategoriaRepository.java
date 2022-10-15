package com.grabas.gestaoprodutos.modulos.categoria.repository;

import com.grabas.gestaoprodutos.modulos.categoria.model.Categoria;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends PagingAndSortingRepository<Categoria, Integer> {
}
