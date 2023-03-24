package com.counterstrike.inventario.services;

import com.counterstrike.inventario.searchresults.SkinsSearch;
import com.counterstrike.inventario.repositories.SkinsRestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkinRestService {

    private final SkinsRestRepository skinsRestRepository;

    public SkinRestService(SkinsRestRepository skinsRestRepository){
        this.skinsRestRepository = skinsRestRepository;
    }

    public List<SkinsSearch> buscar(){
        return skinsRestRepository.search();
    }

}
