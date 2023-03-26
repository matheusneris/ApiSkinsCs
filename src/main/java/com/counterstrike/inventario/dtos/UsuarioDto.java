package com.counterstrike.inventario.dtos;


import com.counterstrike.inventario.entities.SkinModel;
import com.counterstrike.inventario.entities.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private String nomeUsuario;
    private String email;
    private List<SkinModel> inventarioSkins;

    public UsuarioDto(UsuarioModel usuarioModel){
        this.nomeUsuario = usuarioModel.getNomeUsuario();
        this.email = usuarioModel.getEmail();
        this.inventarioSkins = usuarioModel.getInventarioSkins();
    }

}