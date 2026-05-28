package com.patrick.bff_agendador_tarefas.insfrastructure.client.config;

import com.patrick.bff_agendador_tarefas.insfrastructure.exceptions.BusinessException;
import com.patrick.bff_agendador_tarefas.insfrastructure.exceptions.ConflictException;
import com.patrick.bff_agendador_tarefas.insfrastructure.exceptions.IllegalArgumentException;
import com.patrick.bff_agendador_tarefas.insfrastructure.exceptions.ResourceNotFoundException;
import com.patrick.bff_agendador_tarefas.insfrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {

        String mensagemErro= mensaggemErro(response);
        switch (response.status()){
            case 409:
                return new ConflictException("Erro: " + mensagemErro);
            case 403:
                return new ResourceNotFoundException( "Erro: " + mensagemErro);
            case 400:
                return new IllegalArgumentException("Erro: " + mensagemErro);
            case 401:
                return new UnauthorizedException("Erro: " + mensagemErro);
            default:
                return new BusinessException("Erro: " + mensagemErro);
        }
    }

    private String mensaggemErro(Response response){
        try {
            if(Objects.isNull(response.body())){
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes(),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
