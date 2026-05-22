package com.patrick.bff_agendador_tarefas.controller;


import com.patrick.bff_agendador_tarefas.business.TarefasService;
import com.patrick.bff_agendador_tarefas.business.dto.in.TarefasDTORequest;
import com.patrick.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.patrick.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import com.patrick.bff_agendador_tarefas.insfrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastra tarefas de usuário")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {
    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salva tarefas de Usuário", description = "Salva tarefas de usuário")
    @ApiResponse(responseCode = "200", description = "tarefa salvo com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401" , description = "Usuário não autorizado")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest tarefasDTO,
                                                            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, tarefasDTO));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas de Usuário por periodo", description = "Busca tarefas de usuário por periodo")
    @ApiResponse(responseCode = "200", description = "tarefa encontrada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401" , description = "Usuário não autorizado")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial, @RequestParam @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal, @RequestHeader(name = "Authorization", required =
            false) String token) {

        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));

    }

    @GetMapping
    @Operation(summary = "Busca lista tarefas de Usuário", description = "Busca lista tarefas de usuário")
    @ApiResponse(responseCode = "200", description = "Lista de tarefas encontrada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403" , description = "Email não encontrado")
    @ApiResponse(responseCode = "401" , description = "Usuário não autorizado")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(@RequestHeader(name = "Authorization",
            required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por Id", description = "Deleta tarefas por Id")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403" , description = "Tarefa id não encontrado")
    @ApiResponse(responseCode = "401" , description = "Usuário não autorizado")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization", required = false) String token) {
        tarefasService.deletaTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status da tarefa", description = "Altera status da tarefa")
    @ApiResponse(responseCode = "200", description = "Status da tarefa alterada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403" , description = "Tarefa id não encontrado")
    @ApiResponse(responseCode = "401" , description = "Usuário não autorizado")
    public ResponseEntity<TarefasDTOResponse> alteraStatusDaTarefa(@RequestParam("status") StatusNotificacaoEnum status,
                                                                   @RequestParam("id") String id,
                                                                   @RequestHeader(name = "Authorization", required =
                                                                           false) String token) {
        return ResponseEntity.ok(tarefasService.alterarStatusTarefa(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Atualiza dados da tarefa", description = "Atualiza dados da tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403" , description = "Tarefa id não encontrado")
    @ApiResponse(responseCode = "401" , description = "Usuário não autorizado")
    public ResponseEntity<TarefasDTOResponse> updateTarefa(@RequestBody TarefasDTORequest tarefasDTO, @RequestParam(
            "id") String id,
                                                           @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.updateTarefa(tarefasDTO, id, token));
    }

}


