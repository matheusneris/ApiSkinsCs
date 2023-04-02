package com.counterstrike.inventario.repositories;

import com.counterstrike.inventario.entities.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    boolean existsByEmail(String email);

    Optional<Object> findByNomeUsuario(String nomeUsuario);
}
