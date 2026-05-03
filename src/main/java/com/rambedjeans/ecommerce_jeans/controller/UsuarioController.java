package com.rambedjeans.ecommerce_jeans.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rambedjeans.ecommerce_jeans.model.Usuario;
import com.rambedjeans.ecommerce_jeans.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController { 

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // GET  /api/usuarios- List all usuars
    @GetMapping
    public ResponseEntity<List<Usuario>> listAll() {

        //  Llamar al service
        List<Usuario> usuarios = usuarioService.getAll();

        // Crear manualmente la respuesta HTTP 200
        ResponseEntity<List<Usuario>> respuesta =
                new ResponseEntity<>(usuarios, HttpStatus.OK);

        // Retornar la respuesta
        return respuesta;
    }

        /* @GetMapping
        public ResponseEntity<List<Usuario>> listAll() {
            List<Usuario> usuarios = usuarioService.getAll();
            return ResponseEntity.ok(usuarios);
        } */


   // GET /api/usuarios/{id} - GET USERS by ID

   @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Integer id) {

        Usuario usuario = usuarioService.getById(id);

    if (usuario != null) {

             return new ResponseEntity<>(usuario, HttpStatus.OK);

    } else {

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }


    /*   @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable String id) {
        Optional<Usuario> usuario = usuarioService.getById(id);
        return usuario
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    } */

    
     // POST /api/usuarios - Create a new user
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario newUsuario = usuarioService.save(usuario);

        ResponseEntity<Usuario> respuesta =
        new ResponseEntity<>(newUsuario, HttpStatus.CREATED);

        return respuesta;

        //return ResponseEntity.status(HttpStatus.CREATED).body(newUsuario);
    }

    // PUT /api/usuarios/{id} - Update user
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Integer id,
                                            @RequestBody Usuario usuarioNuevo) {
    
        Usuario usuarioActualizado = usuarioService.update(id,usuarioNuevo);
    
        // 5️⃣ Devolver 200 OK (NO 201)
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }

        // DELETE /api/usuarios/{id} - Deactivate user  
        @DeleteMapping("/{id}")
        public ResponseEntity<Usuario> desactivar(@PathVariable Integer id) {
           Usuario activated = usuarioService.deactivate(id);
    
        return ResponseEntity.ok(activated);

    }

        //activate
        @PatchMapping("/{id}/activate")
        public ResponseEntity<Usuario> activate(@PathVariable Integer id) {
            Usuario deactivated = usuarioService.activate(id);
            return ResponseEntity.ok(deactivated);
    }

    
    
}
