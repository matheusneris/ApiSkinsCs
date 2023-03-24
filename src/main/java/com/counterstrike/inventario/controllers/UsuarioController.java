package com.counterstrike.inventario.controllers;

import com.counterstrike.inventario.dtos.UsuarioDto;
import com.counterstrike.inventario.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Object> criarUsuario(@RequestBody @Valid UsuarioDto usuarioDto){
        if(usuarioService.usuarioExistByEmail(usuarioDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Um usuário já existente com este email.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuarioDto));
    }

    @GetMapping
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarUsuarioPorId(@PathVariable Long id){
        usuarioService.excluirUsuarioPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário excluído.");
    }

}
