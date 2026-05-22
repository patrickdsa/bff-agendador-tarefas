package com.patrick.bff_agendador_tarefas.insfrastructure.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
    public BusinessException (String message, Throwable throwable){ super(message, throwable);}
}
