package com.counterstrike.inventario.repositories;

import com.counterstrike.inventario.entities.InventarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<InventarioModel, Long> {



}
