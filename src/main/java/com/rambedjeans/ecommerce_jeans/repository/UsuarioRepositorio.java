package com.rambedjeans.ecommerce_jeans.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.rambedjeans.ecommerce_jeans.model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    // Spring Data JPA genera automáticamente la implementación 
    //hace el crud: save(), findAll(), findById(), deleteById(), etc.

    //esta la necesito para el auth, register login
    Optional<Usuario> findByCorreo(String correo);

    
}
