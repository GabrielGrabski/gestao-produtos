package com.grabas.gestaoprodutos.modulos.categoria.service;

import com.grabas.gestaoprodutos.modulos.categoria.dto.CategoriaRequest;
import com.grabas.gestaoprodutos.modulos.categoria.dto.CategoriaResponse;
import com.grabas.gestaoprodutos.modulos.categoria.model.Categoria;
import com.grabas.gestaoprodutos.modulos.categoria.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public CategoriaResponse save(CategoriaRequest request) {
        var categoria = repository.save(Categoria.to(request));
        return new CategoriaResponse(categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.getProdutos());
    }

    public Page<Categoria> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
