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

         // GET - List all usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> listAll() {

        //  Llamar al service
        List<Usuario> usuarios = usuarioService.getAll();

        // Crear manualmente la respuesta HTTP 200
        ResponseEntity<List<Usuario>> respuesta =
                new ResponseEntity<>(usuarios, HttpStatus.OK);

        // 3Retornar la respuesta
        return respuesta;
    }

        /* @GetMapping
        public ResponseEntity<List<Usuario>> listAll() {
            List<Usuario> usuarios = usuarioService.getAll();
            return ResponseEntity.ok(usuarios);
        } */


   // GET /api/usuarios/{id} - get ref by ID

   @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable String id) {

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

    
     // POST /api/referencias - Create a new ref
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario newUsuario = usuarioService.save(usuario);

        ResponseEntity<Usuario> respuesta =
        new ResponseEntity<>(newUsuario, HttpStatus.CREATED);

        return respuesta;

        //return ResponseEntity.status(HttpStatus.CREATED).body(newUsuario);
    }

        // PUT /api/usuarios/{id} - Update ref
        @PutMapping("/{id}")
        public ResponseEntity<Usuario> update(@PathVariable String id,
                                              @RequestBody Usuario usuarioNuevo) {
        
            // 1️⃣ Buscar el usuario existente
            Usuario usuarioExistente = usuarioService.getById(id);
        
            // 2️⃣ Si no existe → 404
            if (usuarioExistente == null) {
                return ResponseEntity.notFound().build();
            }
        
            // 3️⃣ Actualizar campos
            usuarioExistente.setActivo(usuarioNuevo.isActivo());
            usuarioExistente.setTipoDocumento(usuarioNuevo.getTipoDocumento());
            // aquí actualizas los campos que quieras permitir cambiar
        
            // 4️⃣ Guardar cambios
            Usuario usuarioActualizado = usuarioService.save(usuarioExistente);
        
            // 5️⃣ Devolver 200 OK (NO 201)
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
        }
    
        // DELETE /api/usuarios/{id} - Deactivate user  
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> desactivar(@PathVariable String id) {
            usuarioService.deactivate(id);
            return ResponseEntity.noContent().build();

    }

        //activate
        @PatchMapping("/{id}/activate")
        public ResponseEntity<Usuario> activate(@PathVariable String id) {
            Usuario usuario = usuarioService.getById(id);
        
            if (usuario == null) {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   
            }
        
            usuario.setActivo(true);
        
        Usuario activated = usuarioService.save(usuario);
        
        return ResponseEntity.ok(activated);
    }

    
    
}
