package com.counterstrike.inventario.auth;

import com.counterstrike.inventario.entities.UsuarioModel;
import com.counterstrike.inventario.repositories.UsuarioRepository;
import com.counterstrike.inventario.system.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Log
public class AuthenticationRestController {

    private final UsuarioRepository userJpaRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request){
        var authentication = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        authenticationManager.authenticate(authentication);
        UsuarioModel user = userJpaRepository.findByUsername(request.username()).orElseThrow();
        String token = jwtService.createToken((UserDetails) user);
        return  new AuthenticationResponse(user.getId(), token);
    }

}
