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

import com.rambedjeans.ecommerce_jeans.model.Variante;
import com.rambedjeans.ecommerce_jeans.service.VarianteService;

@RestController
@RequestMapping("/api/variantes")
public class VarianteController {

    private final VarianteService varianteService;

    public VarianteController(VarianteService varianteService) {
        this.varianteService = varianteService;
    }

    //listar todos

     @GetMapping
    public ResponseEntity<List<Variante>> listAll() {

        //  Llamar al service
        List<Variante> variantes = varianteService.getAll();

        // Crear manualmente la respuesta HTTP 200
        ResponseEntity<List<Variante>> respuesta =
                new ResponseEntity<>(variantes, HttpStatus.OK);

        // 3Retornar la respuesta
        return respuesta;
    }

    // GET /api/variantes/{id} - get var by ID

   @GetMapping("/{id}")
    public ResponseEntity<Variante> getById(@PathVariable Integer id) {

        Variante variante = varianteService.getById(id);

    if (variante != null) {

             return new ResponseEntity<>(variante, HttpStatus.OK);

    } else {

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }

     // POST /api/variante - Create a new variante
    @PostMapping
    public ResponseEntity<Variante> create(@RequestBody Variante variante) {

        Variante newVariante = varianteService.save(variante);

        ResponseEntity<Variante> respuesta =
        new ResponseEntity<>(newVariante, HttpStatus.CREATED);

        return respuesta;

        //return ResponseEntity.status(HttpStatus.CREATED).body(newUsuario);
    }
    
     // PUT /api/variantes/{id} - Update variante
    @PutMapping("/{id}")
    public ResponseEntity<Variante> update(@PathVariable Integer id,
                                        @RequestBody Variante variante) {
    
        // 1️⃣ Buscar el usuario existente
        Variante varianteExistente = varianteService.getById(id);

        
    
        // 2️⃣ Si no existe → 404
        if (varianteExistente == null) {
            return ResponseEntity.badRequest().build();
        }
    
        // 3️⃣ Actualizar campos
        varianteExistente.setIdReferencia(variante.getIdReferencia());
        varianteExistente.setIdTalla(variante.getIdTalla());
        varianteExistente.setIdColor(variante.getIdColor());
        varianteExistente.setSku(variante.getSku());
        varianteExistente.setActivo(variante.isActivo());
             
        // aquí actualizas los campos que quieras permitir cambiar
    
        // 4️⃣ Guardar cambios
        Variante varianteUpdate = varianteService.save(varianteExistente);
    
        // 5️⃣ Devolver 200 OK (NO 201)
        return new ResponseEntity<Variante>(varianteUpdate, HttpStatus.OK);
    }

     // DELETE /api/variantes/{id} - Deactivate variante  
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Integer id) {
        varianteService.deactivate(id);
        return ResponseEntity.noContent().build();

    }

    //activate
    @PatchMapping("/{id}/activate")
    public ResponseEntity<Variante> activate(@PathVariable Integer id) {
        Variante variante = varianteService.getById(id);
    
        if (variante == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    
        variante.setActivo(true);
    
    Variante activated = varianteService.save(variante);
    
    return ResponseEntity.ok(activated);
    }

    
}
