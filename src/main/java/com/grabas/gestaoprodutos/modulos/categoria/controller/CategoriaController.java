package com.grabas.gestaoprodutos.modulos.categoria.controller;

import com.grabas.gestaoprodutos.modulos.categoria.dto.CategoriaRequest;
import com.grabas.gestaoprodutos.modulos.categoria.dto.CategoriaResponse;
import com.grabas.gestaoprodutos.modulos.categoria.model.Categoria;
import com.grabas.gestaoprodutos.modulos.categoria.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public Page<Categoria> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @PostMapping
    public CategoriaResponse save(@RequestBody CategoriaRequest request) {
        return service.save(request);
    }
}
