package com.grabas.gestaoprodutos.comum.exception.controller;

import com.grabas.gestaoprodutos.comum.exception.model.ExResponse;
import com.grabas.gestaoprodutos.comum.exception.model.ValidacaoException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExHandlerController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ValidacaoException.class)
    public List<ExResponse> getErrorMessage(ValidacaoException ex) {
        return List.of(new ExResponse(ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ExResponse> getErrorMessage(MethodArgumentNotValidException ex) {
        var errors = new ArrayList<ExResponse>();

        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> errors.add(new ExResponse(error.getDefaultMessage())));

        return errors;
    }
}
