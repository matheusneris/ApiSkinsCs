package com.counterstrike.inventario.controllers;

import com.counterstrike.inventario.dtos.UsuarioDto;
import com.counterstrike.inventario.requests.UsuarioRequest;
import com.counterstrike.inventario.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/rest/usuario")
public class UsuarioRestController {

    private UsuarioService usuarioService;

    public UsuarioRestController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Object> criarUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest){
        if(usuarioService.usuarioExistByEmail(usuarioRequest.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Um usuário já existente com este email.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuarioRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editarUsuario(@PathVariable Long id, @RequestBody UsuarioRequest usuarioRequest){
        Optional<UsuarioDto> usuarioDto = usuarioService.buscarUsuarioPorId(id);
        if(usuarioDto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe usuário com este id.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.editarUsuario(id , usuarioRequest));
    }

    @GetMapping("/listartodos")
    public ResponseEntity<Object> listarTodosUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarTodosUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarUsuarioPorId(@PathVariable Long id){
        Optional<UsuarioDto> usuarioDto =  usuarioService.buscarUsuarioPorId(id);
        if(usuarioDto.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(usuarioDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe usuário com este id.");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> buscarUsuarioPorId(@RequestBody UsuarioRequest usuarioReques){
        boolean containsUser =  usuarioService.login(usuarioReques.getEmail(), usuarioReques.getSenha());
        if(containsUser){
            return ResponseEntity.status(HttpStatus.OK).body("Login realizado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email ou senha incorretos");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Object> deletarUsuarioPorId(@PathVariable Long id){
        usuarioService.excluirUsuarioPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário excluído.");
    }

}
