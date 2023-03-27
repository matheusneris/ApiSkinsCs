package com.counterstrike.inventario.dtos;

import com.counterstrike.inventario.entities.SkinModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SkinDto{

    private String id;
    private String name;
    private String pattern;
    private String rarity;
    private String image;

    public SkinDto(SkinModel skinModel){
        this.id = skinModel.getId();
        this.name = skinModel.getName();
        this.pattern = skinModel.getPattern();
        this.rarity = skinModel.getRarity();
        this.image = skinModel.getImage();
    }


}
