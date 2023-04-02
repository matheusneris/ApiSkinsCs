package com.counterstrike.inventario.controllers;

import com.counterstrike.inventario.dtos.UsuarioDto;
import com.counterstrike.inventario.entities.UsuarioModel;
import com.counterstrike.inventario.repositories.UsuarioRepository;
import com.counterstrike.inventario.requests.UsuarioRequest;
import com.counterstrike.inventario.services.UsuarioService;
import com.counterstrike.inventario.system.DuplicatedException;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/rest/usuario")
//@PreAuthorize("hasRole('ADMIN')")
//@RequiredArgsConstructor
@Log4j2
public class UsuarioRestController {


    private UsuarioRepository usuarioRepository;
    private UsuarioService usuarioService;

    private final PasswordEncoder senhaEncoder;

    public UsuarioRestController(UsuarioRepository usuarioRepository, UsuarioService usuarioService, PasswordEncoder senhaEncoder){
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
        this.senhaEncoder = senhaEncoder;
    }

    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto criarUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        if (usuarioService.usuarioExistByEmail(usuarioRequest.getEmail())) {
            throw new DuplicatedException("E-mail já cadastrado");
        }

        UsuarioModel usuario = UsuarioModel.builder()
                .email(usuarioRequest.getEmail())
                .nomeUsuario(usuarioRequest.getNomeUsuario())
                .role(usuarioRequest.getRole())
                .senha(senhaEncoder.encode(usuarioRequest.getSenha()))
                .build();

        UsuarioModel usuarioModel = this.usuarioRepository.save(usuario);

        return new UsuarioDto(usuarioModel);

    }
//    public ResponseEntity<Object> criarUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest){
//        if(usuarioService.usuarioExistByEmail(usuarioRequest.getEmail())){
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Um usuário já existente com este email.");
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuarioRequest));
//    }

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
