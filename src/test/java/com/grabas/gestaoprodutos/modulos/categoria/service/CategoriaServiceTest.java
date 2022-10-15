package com.grabas.gestaoprodutos.modulos.categoria.service;

import com.grabas.gestaoprodutos.modulos.categoria.model.Categoria;
import com.grabas.gestaoprodutos.modulos.categoria.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.grabas.gestaoprodutos.utils.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CategoriaServiceTest {

    @Autowired
    private CategoriaService service;
    @MockBean
    private CategoriaRepository repository;

    @Test
    public void save_deveSalvarERetornarResponse_quandoRequestDtoCorreto() {
        when(repository.save(any(Categoria.class))).thenReturn(umaCategoria());

        assertThat(service.save(umaCategoriaRequest(umaCategoria())))
                .isEqualTo(umaCategoriaResponse(umaCategoria()));
    }
}
