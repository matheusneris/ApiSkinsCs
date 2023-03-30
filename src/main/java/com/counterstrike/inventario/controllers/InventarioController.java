package com.counterstrike.inventario.controllers;

import com.counterstrike.inventario.dtos.InventarioDto;
import com.counterstrike.inventario.dtos.SkinDto;
import com.counterstrike.inventario.dtos.UsuarioDto;
import com.counterstrike.inventario.entities.SkinModel;
import com.counterstrike.inventario.entities.UsuarioModel;
import com.counterstrike.inventario.requests.InventarioRequest;
import com.counterstrike.inventario.services.InventarioService;
import com.counterstrike.inventario.services.SkinService;
import com.counterstrike.inventario.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/inventario")
@RequiredArgsConstructor
@Log4j2
public class InventarioController {

    private UsuarioService usuarioService;
    private SkinService skinService;
    private InventarioService inventarioService;

    public InventarioController(UsuarioService usuarioService, SkinService skinService, InventarioService inventarioService){
        this.usuarioService = usuarioService;
        this.skinService = skinService;
        this.inventarioService = inventarioService;
    }

    @PostMapping("/salvar/{id}")
    private ResponseEntity<Object> salvarSkinsInventario(@RequestBody InventarioRequest inventarioRequest, @PathVariable Long id){
        Optional<UsuarioDto> usuarioDto = usuarioService.buscarUsuarioPorId(id);
        if(usuarioDto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        }

        Optional<SkinModel> skinModel = skinService.buscarSkinPorId(inventarioRequest.getIdSkin());
        if(skinModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Skin não encontrada.");
        }

        Optional<InventarioDto> inventarioDtoOptional = inventarioService.findByUsuarioModelIdAndSkinModelId(id, skinModel.get().getId());
        if(inventarioDtoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já possui a skin no inventário.");
        }

        InventarioDto inventarioDto = new InventarioDto(getUsuarioModel(id) , skinModel.get());
        inventarioService.salvarSkinNoInventario(inventarioDto);
        return ResponseEntity.status(HttpStatus.OK).body("Skin adicionada ao inventário");
    }

    private UsuarioModel getUsuarioModel(Long id){
        return usuarioService.getUsuarioModel(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarInventario(@PathVariable Long id){
        Optional<UsuarioDto> usuarioDto = usuarioService.buscarUsuarioPorId(id);
        if(usuarioDto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não cadastrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(inventarioService.buscarInventarioUsuario(id));
    }

}