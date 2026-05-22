package com.patrick.bff_agendador_tarefas.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTORequest {
    private String rua;
    private String complemento;
    private Long numero;
    private String cep;
    private String cidade;
    private String estado;
}
