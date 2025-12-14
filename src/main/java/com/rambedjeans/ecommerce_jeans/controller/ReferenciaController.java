package com.rambedjeans.ecommerce_jeans.controller;

import java.util.List;
import java.util.Optional;

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

import com.rambedjeans.ecommerce_jeans.model.Referencia;
import com.rambedjeans.ecommerce_jeans.service.ReferenciaService;

@RestController
@RequestMapping("/api/referencias")
public class ReferenciaController {

    private final ReferenciaService referenciaService;

    public ReferenciaController(ReferenciaService referenciaService) {
        this.referenciaService = referenciaService;
    }

     // GET /api/referencias - List all referencias
    @GetMapping
    public ResponseEntity<List<Referencia>> listAll() {
        List<Referencia> referencias = referenciaService.getAll();
        return ResponseEntity.ok(referencias);
    }

      // GET /api/referencias/{id} - get ref by ID
    @GetMapping("/{id}")
    public ResponseEntity<Referencia> getById(@PathVariable String id) {
        Optional<Referencia> referencia = referenciaService.getbyId(id);
        return referencia
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

     // POST /api/referencias - Create a new ref
    @PostMapping
    public ResponseEntity<Referencia> create(@RequestBody Referencia referencia) {
        Referencia newReferencia = referenciaService.save(referencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(newReferencia);
    }

        // PUT /api/referencias/{id} - Update ref
    @PutMapping("/{id}")
    public ResponseEntity<Referencia> update(@PathVariable String id,@RequestBody Referencia referencia) {
        
        if (!referenciaService.getbyId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        referencia.setIdReferencia(id);
        Referencia refUpdate = referenciaService.save(referencia);
        return ResponseEntity.ok(refUpdate);
    }
    
    // DELETE /api/referencias/{id} - Deactivate ref
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable String id) {
        referenciaService.deactivate(id);
        return ResponseEntity.noContent().build();

   }

    //activate
   @PatchMapping("/{id}/activate")
public ResponseEntity<Referencia> activate(@PathVariable String id) {
    Optional<Referencia> referenciaOpt = referenciaService.getbyId(id);
    
    if (!referenciaOpt.isPresent()) {
        return ResponseEntity.notFound().build();
    }
    
    Referencia referencia = referenciaOpt.get();
    referencia.setActivo(true);
    Referencia activated = referenciaService.save(referencia);
    
    return ResponseEntity.ok(activated);
}



}
