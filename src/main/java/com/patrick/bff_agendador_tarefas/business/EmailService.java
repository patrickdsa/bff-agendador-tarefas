package com.patrick.bff_agendador_tarefas.business;

import com.patrick.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.patrick.bff_agendador_tarefas.insfrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {
    private final EmailClient emailClient;

    public void enviaEmail (TarefasDTOResponse dto){
        emailClient.enviarEmail(dto);
    }
}
