package com.rambedjeans.ecommerce_jeans.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.rambedjeans.ecommerce_jeans.model.Talla;
import com.rambedjeans.ecommerce_jeans.repository.TallaRepository;

@Service
public class TallaService {
    
    private final TallaRepository tallaRepository;

     // Inyección de dependencias por constructor
    // ¿Por qué por constructor? → Es inmutable (final) y facilita testing
    public TallaService(TallaRepository tallaRepository) {
        this.tallaRepository = tallaRepository;
    }

     // Get all sizes
     public List<Talla> getAll() {
        return tallaRepository.findAll();
    }

    public List<Talla> getAllActive() {
        List<Talla> todas = tallaRepository.findAll();
        List<Talla> activas = new ArrayList<>();
    
        for (Talla talla : todas) {
            if (talla.isActivo()) {
                activas.add(talla);
            }
           }
           return activas;
        
        }

     // Find by ID
    // ¿Por qué devuelve Optional? → Puede no existir, manejo seguro de null
    public Optional<Talla> findById(Integer id) {
        return tallaRepository.findById(id);
    }

    public Talla getById(Integer id){
        
        Optional<Talla> optional = tallaRepository.findById(id);

        if(optional.isPresent()){
            return optional.get();
        }else{
            return null;
        }   
    }

    // Save or update
    // ¿Cómo sabe si es crear o actualizar?
    // → Si idTalla es null → INSERT
    // → Si idTalla tiene valor → UPDATE
    public Talla save(Talla talla) {
        // Aquí podrías agregar validaciones de negocio
        // Ejemplo: validar que nombreTalla no esté vacío
        
        if (talla.getNombreTalla() == null || talla.getNombreTalla().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la talla no puede estar vacío");
        }
        
        return tallaRepository.save(talla);
    }

    public void deactivate(Integer id) {
        Optional<Talla> tallaOpt = tallaRepository.findById(id);  // 1. Busca en BD
        
        if (tallaOpt.isPresent()) {           // 2. ¿Existe?
            Talla talla = tallaOpt.get();     // 3. Obtén el objeto
            talla.setActivo(false);           // 4. Cambia a false
            tallaRepository.save(talla);      // 5. Guarda en BD
        }
    }

     // Activar
     public void activate(Integer id) {
        Optional<Talla> tallaOpt = tallaRepository.findById(id);
        if (tallaOpt.isPresent()) {
            Talla talla = tallaOpt.get();
            talla.setActivo(true);
            tallaRepository.save(talla);
        }
    }
    
    // Delete by ID para dejarlo en desarollo no en produccion
    public void delete(Integer id) {
        // Nota: Esto falla si hay relaciones (ej: variantes usando esta talla)
        // Más adelante manejaremos esto con validaciones
        tallaRepository.deleteById(id);
    }
    
    
}
