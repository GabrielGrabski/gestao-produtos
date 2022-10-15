package com.grabas.gestaoprodutos.modulos.categoria.controller;

import com.google.gson.Gson;
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

import static com.grabas.gestaoprodutos.utils.TestUtils.umaCategoria;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        mvc.perform(get("/categorias")).andExpect(status().isOk());
        verify(service, times(1)).findAll(any());
    }

    @Test
    @SneakyThrows
    public void save_deveRetornar200_quandoRequestCorreto() {
        mvc.perform(post("/categorias")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(umaCategoria())))
                .andExpect(status().isOk());

        verify(service, times(1)).save(any(CategoriaRequest.class));
    }
}
