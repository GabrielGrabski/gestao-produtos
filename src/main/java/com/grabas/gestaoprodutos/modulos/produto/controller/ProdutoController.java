package com.grabas.gestaoprodutos.modulos.produto.controller;

import com.grabas.gestaoprodutos.modulos.produto.dto.ProdutoRequest;
import com.grabas.gestaoprodutos.modulos.produto.dto.ProdutoResponse;
import com.grabas.gestaoprodutos.modulos.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public Page<ProdutoResponse> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @PostMapping
    public ProdutoResponse save(@RequestBody ProdutoRequest request) {
        return service.save(request);
    }
}
