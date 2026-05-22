package com.patrick.bff_agendador_tarefas.insfrastructure.client;

import com.patrick.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${notificacao-email.url}")
public interface EmailClient {
    @PostMapping
    void enviarEmail(@RequestBody TarefasDTOResponse dto);
}
