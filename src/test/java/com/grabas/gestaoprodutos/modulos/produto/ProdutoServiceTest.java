package com.grabas.gestaoprodutos.modulos.produto;

import com.grabas.gestaoprodutos.modulos.categoria.repository.CategoriaRepository;
import com.grabas.gestaoprodutos.modulos.produto.repository.ProdutoRepository;
import com.grabas.gestaoprodutos.modulos.produto.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.grabas.gestaoprodutos.utils.TestUtils.umProduto;
import static com.grabas.gestaoprodutos.utils.TestUtils.umProdutoResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProdutoServiceTest {

    @Autowired
    private ProdutoService service;
    @MockBean
    private CategoriaRepository categoriaRepository;
    @MockBean
    private ProdutoRepository repository;

    @Test
    public void findAll_deveRetornarTodosProdutos_quandoHouver() {
        when(repository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(umProduto())));

        assertThat(service.findAll(PageRequest.of(0, 10)))
                .isEqualTo(new PageImpl<>(List.of(umProdutoResponse(umProduto()))));
    }

    @Test
    public void findAll_deveRetornarListaVazia_quandoNaoHouverRegistros() {
        when(repository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of()));

        assertThat(service.findAll(PageRequest.of(0, 10)))
                .isEqualTo(new PageImpl<>(List.of()));
    }
}
