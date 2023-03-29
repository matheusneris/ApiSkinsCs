package com.counterstrike.inventario.repositories;

import com.counterstrike.inventario.entities.InventarioModel;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<InventarioModel, Long>{

    @Query("""
        select i
        from InventarioModel i where i.usuarioModel.id = :usuarioModelId and i.skinModel.id = :skinModelId
    """)
    Optional<InventarioModel> findByUsuarioModelIdAndSkinModelId(@Param("usuarioModelId") Long usuarioModelId, @Param("skinModelId") String skinModelId);

    List<InventarioModel> findByUsuarioModelId(Long id);

}
