package com.counterstrike.inventario.dtos;


import com.counterstrike.inventario.entities.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    @NotBlank
    private String nomeUsuario;
    @NotBlank
    private String email;


    public UsuarioDto(UsuarioModel usuarioModel){
        this.nomeUsuario = usuarioModel.getNomeUsuario();
        this.email = usuarioModel.getEmail();
    }

}