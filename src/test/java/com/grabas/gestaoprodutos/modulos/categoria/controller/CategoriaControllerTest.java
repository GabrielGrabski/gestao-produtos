package com.grabas.gestaoprodutos.modulos.categoria.controller;

import com.google.gson.Gson;
import com.grabas.gestaoprodutos.comum.enums.EStatus;
import com.grabas.gestaoprodutos.comum.exception.model.ExResponse;
import com.grabas.gestaoprodutos.comum.exception.model.ValidacaoException;
import com.grabas.gestaoprodutos.modulos.categoria.dto.CategoriaRequest;
import com.grabas.gestaoprodutos.modulos.categoria.service.CategoriaService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.grabas.gestaoprodutos.comum.enums.EErrors.CATEGORIA_COM_PRODUTOS;
import static com.grabas.gestaoprodutos.utils.TestUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaControllerTest {

    private final Gson gson = new Gson();
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CategoriaService service;

    @Test
    @SneakyThrows
    public void findAll_deveRetornar200_quandoChamado() {
        mvc.perform(get("/api/categorias")).andExpect(status().isOk());
        verify(service, times(1)).findAll(any());
    }

    @Test
    @SneakyThrows
    public void save_deveRetornar200_quandoRequestCorreto() {
        mvc.perform(post("/api/categorias")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(umaCategoria(EStatus.A))))
                .andExpect(status().isOk());

        verify(service, times(1)).save(any(CategoriaRequest.class));
    }

    @Test
    @SneakyThrows
    public void save_deveRetornarBadRequest_quandoRequestIncorreto() {
        var result = mvc.perform(post("/api/categorias")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(gson.toJson(umaCategoriaSemNome())))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(gson.toJson(List.of(umExResponse("Nome não pode ser vazio.")))));

        verify(service, never()).save(any(CategoriaRequest.class));
    }

    @Test
    @SneakyThrows
    public void alterarSituacao_deveRetornar200_quandoRequestCorreto() {
        mvc.perform(put("/api/categorias/1/alterar-situacao")).andExpect(status().isOk());
        verify(service, times(1)).alterarSituacao(eq(1));
    }

    @Test
    @SneakyThrows
    public void alterarSituacao_deveLancarEx_quandoCategoriaASerInativadaTiverProdutos() {
        when(service.alterarSituacao(eq(1)))
                .thenThrow(new ValidacaoException(CATEGORIA_COM_PRODUTOS.getDescricao()));

        mvc.perform(put("/api/categorias/1/alterar-situacao"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(gson.toJson(List.of(new ExResponse(CATEGORIA_COM_PRODUTOS.getDescricao())))));

        verify(service, times(1)).alterarSituacao(eq(1));
    }
}
