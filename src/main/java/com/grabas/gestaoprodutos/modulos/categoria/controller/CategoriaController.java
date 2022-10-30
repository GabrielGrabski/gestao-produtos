package com.grabas.gestaoprodutos.modulos.categoria.controller;

import com.grabas.gestaoprodutos.modulos.categoria.dto.CategoriaRequest;
import com.grabas.gestaoprodutos.modulos.categoria.dto.CategoriaSemProdutosResponse;
import com.grabas.gestaoprodutos.modulos.categoria.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public Page<CategoriaSemProdutosResponse> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @PostMapping
    public CategoriaSemProdutosResponse save(@Valid @RequestBody CategoriaRequest request) {
        return service.save(request);
    }

    @PutMapping("{id}/alterar-situacao")
    public CategoriaSemProdutosResponse alterarSituacao(@PathVariable Integer id) {
        return service.alterarSituacao(id);
    }
}
