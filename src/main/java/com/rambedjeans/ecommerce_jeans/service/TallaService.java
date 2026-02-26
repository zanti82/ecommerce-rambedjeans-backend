package com.rambedjeans.ecommerce_jeans.service;

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

     // Find by ID
    // ¿Por qué devuelve Optional? → Puede no existir, manejo seguro de null
    public Optional<Talla> findById(Integer id) {
        return tallaRepository.findById(id);
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
    
    // Delete by ID
    public void delete(Integer id) {
        // Nota: Esto falla si hay relaciones (ej: variantes usando esta talla)
        // Más adelante manejaremos esto con validaciones
        tallaRepository.deleteById(id);
    }
    
    // Check if exists
    public boolean exists(Integer id) {
        return tallaRepository.existsById(id);
    }
    
    // Count total
    public long count() {
        return tallaRepository.count();
    }
}
