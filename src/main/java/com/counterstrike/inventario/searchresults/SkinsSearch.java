package com.counterstrike.inventario.searchresults;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SkinsSearch {

    private String id;
    private String name;
    private String description;
    private String weapon;
    private String pattern;
    private double min_float;
    private double max_float;
    private String rarity;
    private boolean stattrak;
    private String image;


}
