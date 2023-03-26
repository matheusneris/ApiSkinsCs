package com.counterstrike.inventario.controllers;

import com.counterstrike.inventario.dtos.UsuarioDto;
import com.counterstrike.inventario.services.InventarioService;
import com.counterstrike.inventario.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/inventario")
public class InventarioController {

    private InventarioService inventarioService;
    private UsuarioService usuarioService;

    public InventarioController(UsuarioService usuarioService, InventarioService inventarioService){
        this.usuarioService = usuarioService;
        this.inventarioService = inventarioService;
    }

    @PostMapping("/criar")

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarInventario(@PathVariable Long id){
        Optional<UsuarioDto> usuarioDto = usuarioService.buscarUsuarioPorId(id);
        if(usuarioDto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não cadastrado.");
        }
        //inventarioService.
        return null;
    }


}
