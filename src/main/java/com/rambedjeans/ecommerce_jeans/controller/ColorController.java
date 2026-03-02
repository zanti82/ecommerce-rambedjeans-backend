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

import com.rambedjeans.ecommerce_jeans.model.Color;
import com.rambedjeans.ecommerce_jeans.service.ColorService;

@RestController
@RequestMapping("/api/colores")
public class ColorController {

    private final ColorService colorService;

    // injeccion

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    //listar todos

     @GetMapping
    public ResponseEntity<List<Color>> listAll() {

        //  Llamar al service
        List<Color> colores = colorService.getAll();

        // Crear manualmente la respuesta HTTP 200
        ResponseEntity<List<Color>> respuesta =
                new ResponseEntity<>(colores, HttpStatus.OK);

        // 3Retornar la respuesta
        return respuesta;
    }

       
   // GET /api/colores/{id} - get ref by ID

   @GetMapping("/{id}")
    public ResponseEntity<Color> getById(@PathVariable Integer id) {

        Color color = colorService.getById(id);

    if (color != null) {

             return new ResponseEntity<>(color, HttpStatus.OK);

    } else {

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }

    // POST /api/colores - Create a new color
    @PostMapping
    public ResponseEntity<Color> create(@RequestBody Color color) {

        Color newColor = colorService.save(color);

        ResponseEntity<Color> respuesta =
        new ResponseEntity<>(newColor, HttpStatus.CREATED);

        return respuesta;

        //return ResponseEntity.status(HttpStatus.CREATED).body(newUsuario);
    }
  
    // PUT /api/colores/{id} - Update colore
    @PutMapping("/{id}")
    public ResponseEntity<Color> update(@PathVariable Integer id,
                                        @RequestBody Color newColor) {
    
        // 1️⃣ Buscar el usuario existente
        Color colorExistente = colorService.getById(id);

        
    
        // 2️⃣ Si no existe → 404
        if (colorExistente == null) {
            return ResponseEntity.badRequest().build();
        }
    
        // 3️⃣ Actualizar campos
        colorExistente.setNombreColor(newColor.getNombreColor());
        colorExistente.setCodigoHex(newColor.getCodigoHex());
             
        // aquí actualizas los campos que quieras permitir cambiar
    
        // 4️⃣ Guardar cambios
        Color colorUpdate = colorService.save(colorExistente);
    
        // 5️⃣ Devolver 200 OK (NO 201)
        return new ResponseEntity<>(colorUpdate, HttpStatus.OK);
    }

    // DELETE /api/usuarios/{id} - Deactivate user  
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Integer id) {
        colorService.deactivate(id);
        return ResponseEntity.noContent().build();

    }

    //activate
    @PatchMapping("/{id}/activate")
    public ResponseEntity<Color> activate(@PathVariable Integer id) {
        Color color = colorService.getById(id);
    
        if (color == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    
        color.setActivo(true);
    
    Color activated = colorService.save(color);
    
    return ResponseEntity.ok(activated);
    }
  
}
