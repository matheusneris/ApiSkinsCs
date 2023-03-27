package com.counterstrike.inventario.requests;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventarioRequest {

    @NotBlank
    private String idSkin;

}
