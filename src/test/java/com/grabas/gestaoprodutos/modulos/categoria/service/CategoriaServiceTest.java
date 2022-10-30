package com.grabas.gestaoprodutos.modulos.categoria.service;

import com.grabas.gestaoprodutos.comum.exception.model.ValidacaoException;
import com.grabas.gestaoprodutos.modulos.categoria.model.Categoria;
import com.grabas.gestaoprodutos.modulos.categoria.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
    public void save_deveSalvarERetornarResponse_quandoRequestDtoCorreto() {
        when(repository.save(eq(umaCategoria()))).thenReturn(umaCategoria());

        assertThat(service.save(umaCategoriaRequest(umaCategoria())))
                .isEqualTo(umaCategoriaSemProdutosResponse(umaCategoria()));

        verify(repository, times(1)).save(any(Categoria.class));
    }

    @Test
    public void save_deveRetornarErro_quandoDtoSemNome() {
        assertThatExceptionOfType(ValidacaoException.class)
                .isThrownBy(() -> service.save(umaCategoriaRequestSemNome(umaCategoriaSemNome())))
                .withMessage("teset");

        verify(repository, never()).save(any(Categoria.class));
    }
}
