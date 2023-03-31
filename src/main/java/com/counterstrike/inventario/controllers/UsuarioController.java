package com.counterstrike.inventario.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("novo")
    public String createUser(){
        return "createUser";
    }

}
