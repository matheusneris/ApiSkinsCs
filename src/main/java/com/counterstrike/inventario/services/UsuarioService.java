package com.counterstrike.inventario.services;

import com.counterstrike.inventario.dtos.UsuarioDto;
import com.counterstrike.inventario.entities.UsuarioModel;
import com.counterstrike.inventario.repositories.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDto salvarUsuario(UsuarioDto usuarioDto){
        UsuarioModel usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuarioModel);
        usuarioRepository.save(usuarioModel);
        return usuarioDto;
    }

    public Optional<List<UsuarioDto>> listarTodosUsuarios(){
        List<UsuarioModel> usuariosModels = usuarioRepository.findAll();
        UsuarioDto usuarioDto = new UsuarioDto();
        List<UsuarioDto> usuariosDtos = new ArrayList<>();
        for (UsuarioModel usuario: usuariosModels) {
            BeanUtils.copyProperties(usuario, usuarioDto);
            usuariosDtos.add(usuarioDto);
        }
        return Optional.of(usuariosDtos);
    }

    public Optional<UsuarioDto> buscarUsuarioPorId(Long id){
        Optional<UsuarioModel> usuarioModel = usuarioRepository.findById(id);
        if(usuarioModel.isPresent()){
            UsuarioDto usuarioDto = new UsuarioDto();
            BeanUtils.copyProperties(usuarioModel, usuarioDto);
            return Optional.of(usuarioDto);
        }
        return null;
    }

    public void excluirUsuarioPorId(Long id){
        usuarioRepository.deleteById(id);
    }

    public boolean usuarioExistByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
}