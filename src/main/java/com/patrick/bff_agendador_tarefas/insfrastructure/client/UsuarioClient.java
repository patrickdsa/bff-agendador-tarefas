package com.patrick.bff_agendador_tarefas.insfrastructure.client;


import com.patrick.bff_agendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.patrick.bff_agendador_tarefas.business.dto.in.LoginRequest;
import com.patrick.bff_agendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.patrick.bff_agendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.patrick.bff_agendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.patrick.bff_agendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.patrick.bff_agendador_tarefas.business.dto.out.UsuarioDTOResponse;
import com.patrick.bff_agendador_tarefas.business.dto.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

     @GetMapping
     UsuarioDTOResponse buscaUsuarioPorEmail (@RequestParam ("email") String email,
                                              @RequestHeader ("Authorization") String token);

     @PostMapping
     UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);


     @PostMapping("/login")
     String login (@RequestBody LoginRequest usuarioDTO);

     @DeleteMapping("/{email}")
     void deletaUsuarioPorEmail (@PathVariable String email,
                                 @RequestHeader("Authorization") String token);

     @PutMapping
     UsuarioDTOResponse atualizaUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                        @RequestHeader("Authorization") String token);

     @PutMapping("/endereco")
     EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                          @RequestParam("id") Long id,
                                          @RequestHeader("Authorization") String token);

     @PutMapping("/telefone")
     TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                          @RequestParam("id") Long id,
                                          @RequestHeader("Authorization") String token);

     @PostMapping("/endereco")
     EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                          @RequestHeader("Authorization") String token);

     @PostMapping("/telefone")
     TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                          @RequestHeader("Authorization") String token);
     @GetMapping("/endereco/{cep}/")
     ViaCepDTOResponse buscarDadosCep(@PathVariable("cep") String cep);





}




