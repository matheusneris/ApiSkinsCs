package com.counterstrike.inventario.entities;

import com.counterstrike.inventario.dtos.UsuarioDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nomeUsuario;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String senha;

}
