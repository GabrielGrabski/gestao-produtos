package com.grabas.gestaoprodutos.modulos.categoria.repository;

import com.grabas.gestaoprodutos.comum.enums.EStatus;
import com.grabas.gestaoprodutos.modulos.categoria.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends PagingAndSortingRepository<Categoria, Integer> {

    List<Categoria> findAllByStatusAndIdIn(EStatus status, List<Integer> ids);

    Page<Categoria> findAllByStatus(Pageable pageable, EStatus status);
}
