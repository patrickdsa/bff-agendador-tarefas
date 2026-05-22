package com.patrick.bff_agendador_tarefas.insfrastructure.client.config;

import com.patrick.bff_agendador_tarefas.insfrastructure.exceptions.BusinessException;
import com.patrick.bff_agendador_tarefas.insfrastructure.exceptions.ConflictException;
import com.patrick.bff_agendador_tarefas.insfrastructure.exceptions.ResourceNotFoundException;
import com.patrick.bff_agendador_tarefas.insfrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()){
            case 409:
                return new ConflictException("Erro atributo já existente ");
            case 403:
                return new ResourceNotFoundException( "Erro atributo não encontrado ");
            case 401:
                return new UnauthorizedException("Erro usuário não autorizado ");
            default:
                return new BusinessException("Erro de servidor");
        }
    }
}
