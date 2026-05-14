package com.patrick.bff_agendador_tarefas.business;


import com.patrick.bff_agendador_tarefas.business.dto.in.TarefasDTORequest;
import com.patrick.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.patrick.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import com.patrick.bff_agendador_tarefas.insfrastructure.client.TarefaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TarefasService {
    private final TarefaClient tarefaClient;

    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest tarefasDTO) {
        return tarefaClient.gravarTarefas(tarefasDTO, token);
    }


    public List<TarefasDTOResponse> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal,
                                                                    String token) {
        return tarefaClient.buscaTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefasDTOResponse> buscaTarefasPorEmail(String token) {
        return tarefaClient.buscaTarefasPorEmail(token);

    }

    public void deletaTarefaPorId(String id, String token) {
        tarefaClient.deletaTarefaPorId(id, token);
    }

    public TarefasDTOResponse alterarStatusTarefa(StatusNotificacaoEnum status, String id, String token) {
        return tarefaClient.alteraStatusDaTarefa(status, id, token);

    }

    public TarefasDTOResponse updateTarefa(TarefasDTORequest tarefasDTO, String id, String token) {
        return tarefaClient.updateTarefa(tarefasDTO, id, token);
    }
}