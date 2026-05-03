package com.rambedjeans.ecommerce_jeans.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rambedjeans.ecommerce_jeans.dto.LoginRequestDTO;
import com.rambedjeans.ecommerce_jeans.dto.LoginResponseDTO;
import com.rambedjeans.ecommerce_jeans.dto.RegisterRequestDTO;
import com.rambedjeans.ecommerce_jeans.model.Usuario;
import com.rambedjeans.ecommerce_jeans.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
        private final AuthService authService;
    
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    // POST /api/auth/register - Registro de nuevos usuarios
    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody RegisterRequestDTO request) {
        Usuario nuevoUsuario = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }
    
    // POST /api/auth/login - Inicio de sesión
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    
}
