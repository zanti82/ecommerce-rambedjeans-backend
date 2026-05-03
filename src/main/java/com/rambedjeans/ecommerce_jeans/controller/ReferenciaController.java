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
        Referencia referencia = referenciaService.getbyId(id);

    if (referencia != null) {

             return new ResponseEntity<>(referencia, HttpStatus.OK);

    } else {

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }

     // POST /api/referencias - Create a new ref
    @PostMapping
    public ResponseEntity<Referencia> create(@RequestBody Referencia referencia) {
        Referencia newReferencia = referenciaService.save(referencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(newReferencia);
    }

        // PUT /api/referencias/{id} - Update ref
    @PutMapping("/{id}")
    public ResponseEntity<Referencia> update(@PathVariable String id,@RequestBody Referencia referenciaNew) {
        
        Referencia referencia = referenciaService.getbyId(id);

        if (referencia == null) {
            
            return ResponseEntity.badRequest().build();
               
        }      
        referencia.setNombreReferencia(referenciaNew.getNombreReferencia());
        referencia.setDescripcion(referenciaNew.getDescripcion());
        referencia.setGenero(referenciaNew.getGenero());
        referencia.setPrecioBase(referenciaNew.getPrecioBase());
        referencia.setEstiloReferencia(referenciaNew.getEstiloReferencia());
        referencia.setActivo(true);

        Referencia referenciaUpdate = referenciaService.save(referencia);
    
        // 5️⃣ Devolver 200 OK (NO 201)
        return new ResponseEntity<>(referenciaUpdate, HttpStatus.OK);

    }
    
    // DELETE /api/referencias/{id} - Deactivate ref
    @DeleteMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivate(@PathVariable String id) {

        Referencia ref = referenciaService.deactivate(id);
    
        return ResponseEntity.ok(
            Map.of(
                "mensaje", "Referencia desactivada correctamente",
                "data", ref
            )
        );
    }

    //activate
   @PatchMapping("/{id}/activate")
public ResponseEntity<Referencia> activate(@PathVariable String id) {
    Referencia referenciaOpt = referenciaService.getbyId(id);
    
    if (referenciaOpt == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
   
    referenciaOpt.setActivo(true);
    Referencia activated = referenciaService.save(referenciaOpt);
    
    return ResponseEntity.ok(activated);
}



}
