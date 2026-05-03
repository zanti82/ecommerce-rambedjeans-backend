package com.rambedjeans.ecommerce_jeans.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rambedjeans.ecommerce_jeans.dto.LoginRequestDTO;
import com.rambedjeans.ecommerce_jeans.dto.LoginResponseDTO;
import com.rambedjeans.ecommerce_jeans.dto.RegisterRequestDTO;
import com.rambedjeans.ecommerce_jeans.model.Role;
import com.rambedjeans.ecommerce_jeans.model.Usuario;
import com.rambedjeans.ecommerce_jeans.repository.UsuarioRepositorio;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
     private final UsuarioRepositorio usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    
    //iyeccion
    public AuthService(UsuarioRepositorio usuarioRepository, 
                      PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

       // Registro de nuevos usuarios
    public Usuario register(RegisterRequestDTO request) {
        
          // Validar que la identificación no esté registrada
          Optional<Usuario> existente = usuarioRepository.findByCorreo(request.getIdentificacion());
          if (existente.isPresent()) {
            throw new RuntimeException("La identificación ya está registrada");
        }
        // Validar que el correo no esté registrado
        Optional<Usuario> correo = usuarioRepository.findByCorreo(request.getCorreo());
        if (correo.isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }
        
              
        // Crear nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setIdentificacion(request.getIdentificacion());
        usuario.setCorreo(request.getCorreo());
        usuario.setNombre(request.getNombre());
        usuario.setTelefono(request.getTelefono());
        usuario.setDireccion(request.getDireccion());
        usuario.setTipoDocumento(request.getTipoDocumento());

        //atriburos por defecto
        usuario.setFechaCreacion(LocalDateTime.now());
        usuario.setRol(Role.USER);
        usuario.setActivo(true);
        usuario.setUltimoAcceso(LocalDateTime.now());
        
        // Encriptar contraseña
        String passwordEncriptado = passwordEncoder.encode(request.getPassword());
        usuario.setPassword(passwordEncriptado);
              
        // Guardar en BD
        return usuarioRepository.save(usuario);
    }
    
    // Login (autenticación)
    public LoginResponseDTO login(LoginRequestDTO request) {
        
        // Buscar usuario por correo
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(request.getCorreo());
        
        if (!usuarioOpt.isPresent()) {
            throw new RuntimeException("Credenciales inválidas");
        }
        
        Usuario usuario = usuarioOpt.get();
        
        // Verificar que esté activo
        if (!usuario.getActivo()) {
            throw new RuntimeException("Usuario inactivo");
        }

        System.out.println(usuario.getPassword());
        
        // Verificar contraseña
        boolean passwordMatch = passwordEncoder.matches(
            request.getPassword(),      // Contraseña ingresada
            usuario.getPassword()        // Contraseña encriptada en BD
        );
        
        if (!passwordMatch) {
            throw new RuntimeException("Credenciales inválidas");
            
        }
        
        // Login exitoso - crear respuesta
        LoginResponseDTO response = new LoginResponseDTO();
        response.setIdentificacion(usuario.getIdentificacion());
        response.setCorreo(usuario.getCorreo());
        response.setRol(usuario.getRol().name());
        response.setMessage("Login exitoso");
        // response.setToken(...);  // Después implementamos JWT
        
        return response;
    }


    
}
