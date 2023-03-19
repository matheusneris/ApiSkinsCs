package com.counterstrike.inventario.controllers;

import com.counterstrike.inventario.services.SkinService;
import com.counterstrike.inventario.searchresults.SkinsSearch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/skins")
public class SkinsRestController {

    private final SkinService skinService;

    public SkinsRestController(SkinService skinService){
        this.skinService = skinService;
    }

    @GetMapping
    public List<SkinsSearch> buscar(){
        return skinService.buscar();
    }

}
