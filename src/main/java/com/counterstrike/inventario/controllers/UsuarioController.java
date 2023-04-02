package com.counterstrike.inventario.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @PostMapping("login")
    public String login(){
        return "login";
    }

    @PostMapping("novo")
    public String createUser(){
        return "createUser";
    }

}
