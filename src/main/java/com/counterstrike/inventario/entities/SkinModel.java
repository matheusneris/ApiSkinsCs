package com.counterstrike.inventario.entities;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SkinModel {

    @Id
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String pattern;
    @Column(nullable = false)
    private String rarity;
    @Column(nullable = false)
    private String image;

}