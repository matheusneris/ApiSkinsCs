package com.counterstrike.inventario.services;

import com.counterstrike.inventario.dtos.UsuarioDto;
import com.counterstrike.inventario.entities.SkinModel;
import com.counterstrike.inventario.entities.UsuarioModel;
import com.counterstrike.inventario.repositories.UsuarioRepository;
import com.counterstrike.inventario.requests.UsuarioRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService extends Throwable {

    private UsuarioRepository usuarioRepository;
    private SkinService skinService;

    public UsuarioService(UsuarioRepository usuarioRepository, SkinService skinService){
        this.usuarioRepository = usuarioRepository;
        this.skinService = skinService;
    }

    public UsuarioDto salvarUsuario(UsuarioRequest usuarioRequest){
        UsuarioModel usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioRequest, usuarioModel);
        usuarioRepository.save(usuarioModel);
        UsuarioDto usuarioDto = new UsuarioDto(usuarioModel);
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
            return Optional.of(new UsuarioDto(usuarioModel.get()));
        }
        return null;
    }

    public UsuarioModel getUsuarioModel(Long id){
        Optional<UsuarioModel> usuarioModel = usuarioRepository.findById(id);
        if (usuarioModel.isEmpty()){
            return null;
        }
        return usuarioModel.get();
    }

 /*   public UsuarioDto salvarSkinNoInventario(Long id, String idSkin){

        UsuarioModel usuarioModel = usuarioRepository.findById(id).get();
        Optional<SkinModel> skinModel = skinService.buscarSkinPorId(idSkin);
        System.out.println(skinModel);
        if(skinModel.isEmpty()){
            throw new RuntimeException("skin não encontrada");
        }
        usuarioModel.adicionarSkinAoInventario(skinModel.get());
        UsuarioModel usuarioModel1 = usuarioRepository.save(usuarioModel);
        return new UsuarioDto(usuarioModel1);
    }*/

    public void excluirUsuarioPorId(Long id){
        usuarioRepository.deleteById(id);
    }

    public boolean usuarioExistByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
}