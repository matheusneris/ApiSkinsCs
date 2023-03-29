package com.counterstrike.inventario.dtos;

import com.counterstrike.inventario.entities.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private String nomeUsuario;
    private String email;

    public UsuarioDto(UsuarioModel usuarioModel){
        this.nomeUsuario = usuarioModel.getNomeUsuario();
        this.email = usuarioModel.getEmail();
    }

}