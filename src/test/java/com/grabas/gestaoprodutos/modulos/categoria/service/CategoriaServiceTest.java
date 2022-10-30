package com.grabas.gestaoprodutos.modulos.categoria.service;

import com.grabas.gestaoprodutos.comum.enums.EStatus;
import com.grabas.gestaoprodutos.comum.exception.model.ValidacaoException;
import com.grabas.gestaoprodutos.modulos.categoria.model.Categoria;
import com.grabas.gestaoprodutos.modulos.categoria.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static com.grabas.gestaoprodutos.comum.enums.EErrors.CATEGORIA_COM_PRODUTOS;
import static com.grabas.gestaoprodutos.comum.enums.EStatus.A;
import static com.grabas.gestaoprodutos.comum.enums.EStatus.I;
import static com.grabas.gestaoprodutos.utils.TestUtils.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CategoriaServiceTest {

    @Autowired
    private CategoriaService service;
    @MockBean
    private CategoriaRepository repository;

    @Test
    public void findAll_deveRetornarTodos_quandoHouver() {
        var categoria = umaCategoria(A);
        categoria.setId(null);

        when(repository.findAllByStatus(eq(umPageRequest()), eq(A)))
                .thenReturn(new PageImpl<>(List.of(categoria)));

        assertThat(service.findAll(umPageRequest()).getContent())
                .isEqualTo(List.of(umaCategoriaSemProdutosResponse(categoria)));
    }

    @Test
    public void findAll_deveRetornarEmpty_quandoNaoHouver() {
        when(repository.findAllByStatus(eq(umPageRequest()), eq(A)))
                .thenReturn(new PageImpl<>(List.of()));

        assertThat(service.findAll(umPageRequest()).getContent())
                .isEqualTo(List.of());
    }

    @Test
    public void save_deveSalvarERetornarResponse_quandoRequestDtoCorreto() {
        var categoria = umaCategoria(A);
        categoria.setId(null);

        when(repository.save(eq(umaCategoria(A)))).thenReturn(umaCategoria(A));

        assertThat(service.save(umaCategoriaRequest(categoria))).isEqualTo(umaCategoriaSemProdutosResponse(categoria));

        verify(repository, times(1)).save(any(Categoria.class));
    }

    @Test
    public void alterarSituacao_deveLancarEx_quandoInativarCategoriaComProdutos() {
        when(repository.findById(eq(ID))).thenReturn(Optional.of(umaCategoriaComProdutos()));

        assertThatExceptionOfType(ValidacaoException.class)
                .isThrownBy(() -> service.alterarSituacao(ID))
                .withMessage(CATEGORIA_COM_PRODUTOS.getDescricao());
        assertThat(service.alterarSituacao(ID).status()).isEqualTo(A);
    }

    @Test
    public void alterarSituacao_deveInativar_quandoCategoriaNaoPossuirProdutos() {
        when(repository.findById(eq(ID))).thenReturn(Optional.of(umaCategoria(A)));

        assertThat(service.alterarSituacao(ID).status()).isEqualTo(I);
        verify(repository, times(1)).save(any(Categoria.class));

        assertThatCode(() -> service.alterarSituacao(ID)).doesNotThrowAnyException();
    }

    @Test
    public void alterarSituacao_deveAtivar_quandoCategoriaInativa() {
        when(repository.findById(eq(ID))).thenReturn(Optional.of(umaCategoria(I)));

        assertThat(service.alterarSituacao(ID).status()).isEqualTo(A);
        verify(repository, times(1)).save(any(Categoria.class));
    }
}
