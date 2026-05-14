package com.patrick.bff_agendador_tarefas.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTOResponse {
    private Long id;
    private String rua;
    private String complemento;
    private Long numero;
    private String cep;
    private String cidade;
    private String estado;
}
