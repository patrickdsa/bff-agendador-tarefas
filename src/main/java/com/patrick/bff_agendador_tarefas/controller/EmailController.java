package com.patrick.bff_agendador_tarefas.controller;



import com.patrick.bff_agendador_tarefas.business.EmailService;
import com.patrick.bff_agendador_tarefas.business.dto.in.TarefasDTORequest;
import com.patrick.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    @Operation(summary = "Envia notificacao por Email", description = "Envia notificacao por Email")
    @ApiResponse(responseCode = "200", description = "Notificacao enviada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> enviarEmail(@RequestBody TarefasDTOResponse dto){
        emailService.enviaEmail(dto);
        return ResponseEntity.ok().build();
    }




}
