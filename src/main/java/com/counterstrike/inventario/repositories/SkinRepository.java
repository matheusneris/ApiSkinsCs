package com.counterstrike.inventario.repositories;

import com.counterstrike.inventario.entities.SkinModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkinRepository extends JpaRepository<SkinModel, String> {

    List<SkinModel> findByNameContaining(String nome);

    Optional<SkinModel> findById (Long id);
}
