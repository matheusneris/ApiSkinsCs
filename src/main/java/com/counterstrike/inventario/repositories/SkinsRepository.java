package com.counterstrike.inventario.repositories;

import com.counterstrike.inventario.searchresults.SkinsSearch;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "skinsCsRest", url = "${skins.url}")
public interface SkinsRepository {

    @GetMapping("/skins.json")
    List<SkinsSearch> search();

}