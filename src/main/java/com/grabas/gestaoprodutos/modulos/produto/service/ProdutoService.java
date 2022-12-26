package com.grabas.gestaoprodutos.modulos.produto.service;

import com.grabas.gestaoprodutos.comum.enums.EStatus;
import com.grabas.gestaoprodutos.comum.exception.model.ValidacaoException;
import com.grabas.gestaoprodutos.modulos.categoria.dto.CategoriaSemProdutosResponse;
import com.grabas.gestaoprodutos.modulos.categoria.model.Categoria;
import com.grabas.gestaoprodutos.modulos.categoria.repository.CategoriaRepository;
import com.grabas.gestaoprodutos.modulos.categoria.service.CategoriaService;
import com.grabas.gestaoprodutos.modulos.produto.dto.ProdutoRequest;
import com.grabas.gestaoprodutos.modulos.produto.dto.ProdutoResponse;
import com.grabas.gestaoprodutos.modulos.produto.model.Produto;
import com.grabas.gestaoprodutos.modulos.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.grabas.gestaoprodutos.comum.enums.EErrors.CATEGORIA_NAO_ENCONTRADA;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Page<ProdutoResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toProdutoResponse);
    }

    private ProdutoResponse toProdutoResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getStatus(),
                getCategoriasResponse(produto.getCategorias()));
    }

    public void saveCategorias(List<Categoria> categorias, Produto produto) {
        categorias.forEach(categoria -> {
            categoria.getProdutos().add(produto);
            categoriaRepository.save(categoria);
        });
    }

    private Produto setAtivoSeNovoProduto(Produto produto) {
        if (produto.getId() == null) {
            produto.setStatus(EStatus.A);
        }
        return produto;
    }

    public ProdutoResponse save(ProdutoRequest request) {
        var categorias = categoriaRepository.findAllByStatusAndIdIn(EStatus.A, request.categoriasIds());
        validarCategorias(categorias);
        var produto = Produto.to(request, categorias);
        repository.save(setAtivoSeNovoProduto(produto));
        saveCategorias(categorias, produto);
        return toProdutoResponse(produto);
    }

    private List<CategoriaSemProdutosResponse> getCategoriasResponse(List<Categoria> categorias) {
        return categorias.stream()
                .map(categoriaService::toCategoriaSemProdutosResponse)
                .collect(Collectors.toList());
    }

    private void validarCategorias(List<Categoria> categorias) {
        validarCategoriaVazia(categorias);
    }

    private void validarCategoriaVazia(List<Categoria> categorias) {
        if (categorias.isEmpty()) {
            throw new ValidacaoException(CATEGORIA_NAO_ENCONTRADA.getDescricao());
        }
    }
}
