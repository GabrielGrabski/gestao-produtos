package com.grabas.gestaoprodutos.modulos.categoria.service;

import com.grabas.gestaoprodutos.comum.enums.EStatus;
import com.grabas.gestaoprodutos.comum.exception.model.ValidacaoException;
import com.grabas.gestaoprodutos.modulos.categoria.dto.CategoriaRequest;
import com.grabas.gestaoprodutos.modulos.categoria.dto.CategoriaSemProdutosResponse;
import com.grabas.gestaoprodutos.modulos.categoria.model.Categoria;
import com.grabas.gestaoprodutos.modulos.categoria.repository.CategoriaRepository;
import com.grabas.gestaoprodutos.modulos.produto.dto.ProdutoSemCategoriaResponse;
import com.grabas.gestaoprodutos.modulos.produto.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.grabas.gestaoprodutos.comum.enums.EErrors.CATEGORIA_COM_PRODUTOS;
import static com.grabas.gestaoprodutos.comum.enums.EErrors.CATEGORIA_NAO_ENCONTRADA;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public CategoriaSemProdutosResponse alterarSituacao(Integer id) {
        var categoria = getCategoriaById(id);
        setStatus(categoria);
        repository.save(categoria);
        return toCategoriaSemProdutosResponse(categoria);
    }

    public CategoriaSemProdutosResponse toCategoriaSemProdutosResponse(Categoria categoria) {
        return new CategoriaSemProdutosResponse(categoria.getId(),
                categoria.getNome(),
                categoria.getStatus(),
                categoria.getDescricao());
    }

    private Categoria getCategoriaById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ValidacaoException(CATEGORIA_NAO_ENCONTRADA.getDescricao()));
    }

    private void setStatus(Categoria categoria) {
        if (categoria.getStatus().equals(EStatus.A)) {
            categoria.setStatus(EStatus.I);
            validarInativacaoCategoria(categoria);
        } else {
            categoria.setStatus(EStatus.A);
        }
    }

    private void validarInativacaoCategoria(Categoria categoria) {
        if (categoria.getStatus().equals(EStatus.I) && !categoria.getProdutos().isEmpty()) {
            throw new ValidacaoException(CATEGORIA_COM_PRODUTOS.getDescricao());
        }
    }

    public CategoriaSemProdutosResponse save(CategoriaRequest request) {
        var categoria = Categoria.to(request);
        repository.save(setAtivoSeNovaCategoria(categoria));

        return toCategoriaSemProdutosResponse(categoria);
    }

    private Categoria setAtivoSeNovaCategoria(Categoria categoria) {
        if (categoria.getId() == null) {
            categoria.setStatus(EStatus.A);
        }
        return categoria;
    }

    public Page<CategoriaSemProdutosResponse> findAll(Pageable pageable) {
        return repository.findAllByStatus(pageable, EStatus.A)
                .map(this::toCategoriaSemProdutosResponse);
    }
}
