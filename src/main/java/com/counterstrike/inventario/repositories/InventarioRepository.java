package com.counterstrike.inventario.repositories;

import com.counterstrike.inventario.dtos.InventarioDto;
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
        select new com.counterstrike.inventario.dtos.InventarioDto(f.usuarioModel, f.skinModel)
        from InventarioModel f where f.usuarioModel.id = :usuarioModelId and f.skinModel.id = :skinModelId
    """)
    Optional<InventarioDto> findByUsuarioModelIdAndSkinModelId(@Param("usuarioModelId") Long usuarioModelId, @Param("skinModelId") String skinModelId);

    List<InventarioModel> findByUsuarioModelId(Long id);

}
