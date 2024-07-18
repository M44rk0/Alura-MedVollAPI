package com.m44rk0.med_voll_api.controller;

import com.m44rk0.med_voll_api.domain.usuario.DadosAutenticacao;
import com.m44rk0.med_voll_api.domain.usuario.Usuario;
import com.m44rk0.med_voll_api.infra.security.DadosTokenJWT;
import com.m44rk0.med_voll_api.infra.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity fazerLogin(@RequestBody @Valid DadosAutenticacao dados){

        var authToken = new UsernamePasswordAuthenticationToken(dados.usuario(), dados.senha());

        var auth = manager.authenticate(authToken);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(token));
    }

}
