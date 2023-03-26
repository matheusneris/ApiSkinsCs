package com.counterstrike.inventario.services;

import com.counterstrike.inventario.dtos.SkinDto;
import com.counterstrike.inventario.entities.SkinModel;
import com.counterstrike.inventario.repositories.SkinRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SkinService {

    private SkinRepository skinRepository;

    public SkinService(SkinRepository skinRepository){
        this.skinRepository = skinRepository;
    }

    @Transactional
    public void salvarSkin(SkinDto skinDto){
        SkinModel skinModel = new SkinModel();
        BeanUtils.copyProperties(skinDto, skinModel);
        skinRepository.save(skinModel);
    }

    public Optional<SkinModel> buscarSkinPorId(String id){
        Optional <SkinModel> skinModel = skinRepository.findById(id);
        if (skinModel.isPresent()){
            //SkinDto skinDto = new SkinDto();
           // BeanUtils.copyProperties(skinModel, skinDto);
            return skinModel;
        }
        return null;
    }

//    @Transactional
//    public List<SkinModel> salvarListaSkins (List<SkinModel> listaSkins){
//        return skinRepository.saveAll(listaSkins);
//    }

}