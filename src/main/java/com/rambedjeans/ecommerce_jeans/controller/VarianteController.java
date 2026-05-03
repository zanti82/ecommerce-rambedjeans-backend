package com.rambedjeans.ecommerce_jeans.controller;

import java.util.List;
import java.util.Map;

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

import com.rambedjeans.ecommerce_jeans.dto.VarianteDTO;
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
    public ResponseEntity<Variante> create(@RequestBody VarianteDTO dto) {

        Variante newVariante = varianteService.crearVariante(dto);

        ResponseEntity<Variante> respuesta =
        new ResponseEntity<>(newVariante, HttpStatus.CREATED);

        return respuesta;

        //return ResponseEntity.status(HttpStatus.CREATED).body(newUsuario);
    }
    
     // PUT /api/variantes/{id} - Update variante
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                        @RequestBody VarianteDTO dto) {
    
        try {
            Variante variante = varianteService.update(id, dto);

            return ResponseEntity.ok(
                Map.of(
                    "mensaje", "Variante actualizada",
                    "data", variante
                )
            );

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                Map.of("error", e.getMessage())
            );}
    }

     // DELETE /api/variantes/{id} - Deactivate variante  
    @DeleteMapping("/{id}")
    public ResponseEntity<Variante> desactivar(@PathVariable Integer id) {
        Variante activated = varianteService.deactivate(id);
    
        return ResponseEntity.ok(activated);

    }

    //activate
    @PatchMapping("/{id}/activate")
    public ResponseEntity<Variante> activate(@PathVariable Integer id) {
           
        Variante activated = varianteService.activate(id);
    
    return ResponseEntity.ok(activated);
    }

    
}
