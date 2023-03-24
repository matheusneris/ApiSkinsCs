package com.counterstrike.inventario.repositories;

import com.counterstrike.inventario.entities.SkinModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkinRepository extends JpaRepository<SkinModel, String> {



}
