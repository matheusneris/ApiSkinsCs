package com.counterstrike.inventario.services;

import com.counterstrike.inventario.dtos.UsuarioDto;
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
        return new UsuarioDto(usuarioModel);
    }

    public UsuarioDto editarUsuario(Long id, UsuarioRequest usuarioRequest){
        UsuarioModel usuarioModel = usuarioRepository.findById(id).get();
        usuarioModel.setNomeUsuario(usuarioRequest.getNomeUsuario());
        usuarioModel.setEmail(usuarioRequest.getEmail());
        usuarioModel.setSenha(usuarioRequest.getSenha());

        usuarioRepository.save(usuarioModel);

        return new UsuarioDto(usuarioModel);
    }

    public Optional<List<UsuarioDto>> listarTodosUsuarios(){
        List<UsuarioModel> usuariosModels = usuarioRepository.findAll();
        List<UsuarioDto> usuariosDtos = new ArrayList<>();
        for (UsuarioModel usuario: usuariosModels) {
            usuariosDtos.add(new UsuarioDto(usuario));
        }
        return Optional.of(usuariosDtos);
    }

    public Optional<UsuarioDto> buscarUsuarioPorId(Long id){
        Optional<UsuarioModel> usuarioModel = usuarioRepository.findById(id);
        if(usuarioModel.isPresent()){
            return Optional.of(new UsuarioDto(usuarioModel.get()));
        }
        Optional<UsuarioDto> usuarioDto = Optional.empty();
        return usuarioDto;
    }

    public boolean login(String email, String senha) {
        boolean loginSuccess = false;
        List<UsuarioModel> usuariosModels = usuarioRepository.findAll();
        for (UsuarioModel usuario: usuariosModels) {
            System.out.println(usuario.getSenha());
            System.out.println("==");
            System.out.println(senha);
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                loginSuccess = true;
            }
        }
        return loginSuccess;
    }

    public UsuarioModel getUsuarioModel(Long id){
        Optional<UsuarioModel> usuarioModel = usuarioRepository.findById(id);
        if (usuarioModel.isEmpty()){
            return null;
        }
        return usuarioModel.get();
    }

    public void excluirUsuarioPorId(Long id){
        usuarioRepository.deleteById(id);
    }

    public boolean usuarioExistByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
}