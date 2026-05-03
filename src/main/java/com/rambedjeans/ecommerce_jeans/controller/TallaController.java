package com.rambedjeans.ecommerce_jeans.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
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


import com.rambedjeans.ecommerce_jeans.model.Talla;

import com.rambedjeans.ecommerce_jeans.service.TallaService;

@RestController
@RequestMapping("/api/tallas")
public class TallaController {

    private final TallaService tallaService;

    public TallaController(TallaService tallaService) {
        this.tallaService = tallaService;
    }

    // GET /api/tallas 
    @GetMapping
    public ResponseEntity<List<Talla>> listAll() {
        List<Talla> tallas = tallaService.getAll();
        return ResponseEntity.ok(tallas);
    }

      // GET /api/tallas/{id} - get tallas by ID
     @GetMapping("/{id}")
    public ResponseEntity<Talla> getById(@PathVariable Integer id) {

        Talla talla = tallaService.getById(id);

    if (talla != null) {

             return new ResponseEntity<>(talla, HttpStatus.OK);

    } else {

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }

     // POST /api/tallas - Create a new talla
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Talla talla) {

        try {
            Talla newtalla = tallaService.save(talla);
            return ResponseEntity.status(HttpStatus.CREATED).body(newtalla);
            
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("La talla ya existe");
        }
        // lo anterior es para mostrar un emnsaje en postman y no un error 500
        //porque no se pueden repetir nombres
       
    }

        // PUT /api/tallas/{id} - Update ref
    @PutMapping("/{id}")
    public ResponseEntity<Talla> update(@PathVariable Integer id,@RequestBody Talla newTalla) {

        //System.out.println("ID recibido: " + id);
        //System.out.println("Nombre: " + newTalla.getNombreTalla());
        
            Talla talla = tallaService.getById(id);

        if (talla == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    
          
        // actualizar campos permitidos
        talla.setNombreTalla(newTalla.getNombreTalla());
        talla.setActivo(true);
    
        Talla tallaUpdate = tallaService.save(talla);
    
        return ResponseEntity.ok(tallaUpdate);
    }
    
    // DELETE /api/tallas/{id} - Deactivate tallas 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Integer id) {
        tallaService.deactivate(id);
        return ResponseEntity.noContent().build();

   }

    //activate
   @PatchMapping("/{id}/activate")
    public ResponseEntity<Talla> activate(@PathVariable Integer id) {
       
        Talla activated = tallaService.activate(id);
        
        return ResponseEntity.ok(activated);
    }
     
    
}
