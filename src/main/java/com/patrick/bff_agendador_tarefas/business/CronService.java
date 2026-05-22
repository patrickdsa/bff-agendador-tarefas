package com.patrick.bff_agendador_tarefas.business;

import com.patrick.bff_agendador_tarefas.business.dto.in.LoginRequest;
import com.patrick.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.patrick.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CronService {
    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;
    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscarTarefaNaProximaHora(){
        String token = login(converterParaRequestDTO());

        LocalDateTime horaAtual = LocalDateTime.now();
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);

        List<TarefasDTOResponse> listaTarefas = tarefasService.buscaTarefasAgendadasPorPeriodo(horaAtual, horaFutura,
                token);

        listaTarefas.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
            tarefasService.alterarStatusTarefa(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);
        });



    }

    public String login(LoginRequest dto){
        return usuarioService.loginUsuario(dto);
    }

    public LoginRequest converterParaRequestDTO(){
        return LoginRequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
