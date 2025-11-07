package com.restauranteapp.controller;

import com.restauranteapp.model.Usuario;
import com.restauranteapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public static record LoginRequest(String username, String password) {}

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        Optional<Usuario> optUsuario = usuarioRepository.findByUsuario(loginRequest.username());

        if (optUsuario.isEmpty()) {
            return new ResponseEntity<>("Usuário ou senha inválidos", HttpStatus.UNAUTHORIZED);
        }

        Usuario usuario = optUsuario.get();

        // ATENÇÃO: Verificação de senha SIMPLES.
        // Em produção, use Spring Security e BCrypt.
        if (usuario.getSenha().equals(loginRequest.password())) {
            return new ResponseEntity<>("Login bem-sucedido", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuário ou senha inválidos", HttpStatus.UNAUTHORIZED);
        }
    }
}
