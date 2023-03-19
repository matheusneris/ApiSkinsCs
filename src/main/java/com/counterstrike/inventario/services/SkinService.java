package com.counterstrike.inventario.services;

import com.counterstrike.inventario.searchresults.SkinsSearch;
import com.counterstrike.inventario.repositories.SkinsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkinService {

    private final SkinsRepository skinsRepository;

    public SkinService(SkinsRepository skinsRepository){
        this.skinsRepository = skinsRepository;
    }

    public List<SkinsSearch> buscar(){
        return skinsRepository.search();
    }

}
