package com.counterstrike.inventario.services;

import com.counterstrike.inventario.dtos.InventarioDto;
import com.counterstrike.inventario.entities.InventarioModel;
import com.counterstrike.inventario.repositories.InventarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InventarioService{

    private InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository){
        this.inventarioRepository = inventarioRepository;
    }

    public void salvarSkinNoInventario(InventarioDto inventarioDto){
        InventarioModel inventarioModel = new InventarioModel(inventarioDto.getUsuarioModel(), inventarioDto.getSkinModel());
        inventarioRepository.save(inventarioModel);
    }

    public Optional<InventarioDto> findByUsuarioModelIdAndSkinModelId(Long idUsuario, String idSkin){
        return inventarioRepository.findByUsuarioModelIdAndSkinModelId(idUsuario, idSkin);
    }

    public List<InventarioModel> buscarInventarioUsuario(Long id){
        return inventarioRepository.findByUsuarioModelId(id);
    }

}
