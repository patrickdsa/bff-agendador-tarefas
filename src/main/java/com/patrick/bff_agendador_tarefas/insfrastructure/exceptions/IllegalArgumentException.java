package com.patrick.bff_agendador_tarefas.insfrastructure.exceptions;

public class IllegalArgumentException extends RuntimeException {
    public IllegalArgumentException(String message) {
        super(message);
    }
    public IllegalArgumentException(String message, Throwable throwable) {super(message,throwable);}
}

