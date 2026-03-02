package com.rambedjeans.ecommerce_jeans.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.rambedjeans.ecommerce_jeans.model.Variante;
import com.rambedjeans.ecommerce_jeans.repository.VarianteRepository;

public class VarianteService {

    private final VarianteRepository varianteRepository;

    public VarianteService(VarianteRepository varianteRepository) {
        this.varianteRepository = varianteRepository;
    }

    // get all
    public List<Variante> getAll(){
        return varianteRepository.findAll();
    }

    //GET only active color

    public List<Variante> getAllActive() {
        List<Variante> todas = varianteRepository.findAll();
        List<Variante> activas = new ArrayList<>();
    
        for (Variante variante : todas) {
            if (variante.isActivo()) {
                activas.add(variante);
            }
           }
           return activas;
        
        }

    
    public Variante getById(Integer id){
        
        Optional<Variante> optional = varianteRepository.findById(id);

        if(optional.isPresent()){
            return optional.get();
        }else{
            return null;
        }   
    }

    // this hibernate choose from update or save

    public Variante save(Variante variante){
        return varianteRepository.save(variante);
    }

    public void deactivate(Integer id) {
        Optional<Variante> varianteOpt = varianteRepository.findById(id);  // 1. Busca en BD
        
        if (varianteOpt.isPresent()) {           // 2. ¿Existe?
            Variante variante = varianteOpt.get();     // 3. Obtén el objeto
            variante.setActivo(false);           // 4. Cambia a false
            varianteRepository.save(variante);      // 5. Guarda en BD
        }
    }

     // Activar
     public void activate(Integer id) {
        Optional<Variante> varianteOpt = varianteRepository.findById(id);
        if (varianteOpt.isPresent()) {
            Variante variante = varianteOpt.get();
            variante.setActivo(true);
            varianteRepository.save(variante);
        }
    }
    
    // Delete by ID para dejarlo en desarollo no en produccion
    public void delete(Integer id) {
        // Nota: Esto falla si hay relaciones (ej: variantes usando esta talla)
        // Más adelante manejaremos esto con validaciones
        varianteRepository.deleteById(id);
    }


    
}
