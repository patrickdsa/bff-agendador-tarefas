package com.patrick.bff_agendador_tarefas.business;


import com.patrick.bff_agendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.patrick.bff_agendador_tarefas.business.dto.in.LoginRequest;
import com.patrick.bff_agendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.patrick.bff_agendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.patrick.bff_agendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.patrick.bff_agendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.patrick.bff_agendador_tarefas.business.dto.out.UsuarioDTOResponse;
import com.patrick.bff_agendador_tarefas.business.dto.out.ViaCepDTOResponse;
import com.patrick.bff_agendador_tarefas.insfrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO) {
        return usuarioClient.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginRequest usuarioDTO) {
        return usuarioClient.login(usuarioDTO);
    }


    public UsuarioDTOResponse buscaUsuarioPorEmail(String email, String token) {
        return usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token) {
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest usuarioDTO) {
        return usuarioClient.atualizaUsuario(usuarioDTO, token);


    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token) {
        return usuarioClient.atualizaEndereco(enderecoDTO, idEndereco, token);

    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token) {
        return usuarioClient.atualizaTelefone(telefoneDTO, idTelefone, token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest enderecoDTO) {
        return usuarioClient.cadastraEndereco(enderecoDTO, token);

    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest telefoneDTO) {

        return usuarioClient.cadastraTelefone(telefoneDTO, token);

    }

    public ViaCepDTOResponse buscarEnderecoPorCep (String cep){
        return usuarioClient.buscarDadosCep(cep);
    }


}
