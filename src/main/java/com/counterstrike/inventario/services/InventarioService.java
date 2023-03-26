package com.counterstrike.inventario.services;

import com.counterstrike.inventario.dtos.InventarioDto;
import com.counterstrike.inventario.entities.InventarioModel;
import com.counterstrike.inventario.repositories.InventarioRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


public class InventarioService{

    private InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository){
        this.inventarioRepository = inventarioRepository;
    }

    /*public Optional<InventarioModel> buscarInventario(Long id){
        inventarioRepository.findById()
    }*/

}
