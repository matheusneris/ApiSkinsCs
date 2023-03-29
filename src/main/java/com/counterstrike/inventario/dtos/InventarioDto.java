package com.counterstrike.inventario.dtos;

import com.counterstrike.inventario.entities.InventarioModel;
import com.counterstrike.inventario.entities.SkinModel;
import com.counterstrike.inventario.entities.UsuarioModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventarioDto {

    private UsuarioModel usuarioModel;
    private SkinModel skinModel;

    public InventarioDto (UsuarioModel usuarioModel, SkinModel skinModel){
        this.usuarioModel = usuarioModel;
        this.skinModel = skinModel;
    }

    public InventarioDto(InventarioModel inventarioModel){
        this.usuarioModel = inventarioModel.getUsuarioModel();
        this.skinModel = inventarioModel.getSkinModel();
    }

    public InventarioDto() {

    }
}
