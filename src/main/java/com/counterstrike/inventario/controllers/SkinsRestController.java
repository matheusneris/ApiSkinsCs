package com.counterstrike.inventario.controllers;

import com.counterstrike.inventario.dtos.SkinDto;
import com.counterstrike.inventario.services.SkinRestService;
import com.counterstrike.inventario.searchresults.SkinsSearch;
import com.counterstrike.inventario.services.SkinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/skins")
public class SkinsRestController {

    private final SkinRestService skinRestService;
    private final SkinService skinService;

    public SkinsRestController(SkinRestService skinRestService, SkinService skinService){
        this.skinRestService = skinRestService;
        this.skinService = skinService;
    }

    @GetMapping
    public List<SkinsSearch> buscarSkins(){

        List<SkinsSearch> listaSkins = skinRestService.buscar();
        SkinDto skinDto = new SkinDto();
        int contador = 0;
        for (SkinsSearch skin : listaSkins) {

            skinDto.setId(skin.getId());
            skinDto.setName(skin.getName());
            skinDto.setRarity(skin.getRarity());
            skinDto.setPattern(skin.getPattern());
            skinDto.setImage(skin.getImage());

            skinService.salvarSkin(skinDto);
            contador++;
            if(contador==10) break;
        }
        return listaSkins;
    }



}