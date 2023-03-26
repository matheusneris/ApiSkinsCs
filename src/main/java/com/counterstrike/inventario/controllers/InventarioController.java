package com.counterstrike.inventario.controllers;

import com.counterstrike.inventario.dtos.UsuarioDto;
import com.counterstrike.inventario.entities.UsuarioModel;
import com.counterstrike.inventario.requests.InventarioRequest;
import com.counterstrike.inventario.services.InventarioService;
import com.counterstrike.inventario.services.SkinService;
import com.counterstrike.inventario.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/inventario")
public class InventarioController {

    private UsuarioService usuarioService;
    private SkinService skinService;

    public InventarioController(UsuarioService usuarioService, SkinService skinService){
        this.usuarioService = usuarioService;
        this.skinService = skinService;
    }

    @PostMapping("/salvar/{id}")
    private ResponseEntity<Object> salvarSkinsInventario(@RequestBody InventarioRequest inventarioRequest, @PathVariable Long id){
        Optional<UsuarioDto> usuarioDto = usuarioService.buscarUsuarioPorId(id);
        if(usuarioDto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe usuário com este ID.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.salvarSkinNoInventario(id, inventarioRequest.getIdSkin()));
    }


   /* private InventarioService inventarioService;
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
    }*/


}
