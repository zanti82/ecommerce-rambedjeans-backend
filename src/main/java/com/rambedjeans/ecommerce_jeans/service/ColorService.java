package com.rambedjeans.ecommerce_jeans.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.rambedjeans.ecommerce_jeans.model.Color;
import com.rambedjeans.ecommerce_jeans.repository.ColorRepository;

@Service
public class ColorService {

    private ColorRepository colorRepository;
   
    
    //inyeccion
    public ColorService(ColorRepository colorRepository) {
            this.colorRepository = colorRepository;
    }

   
    // get all
    public List<Color> getAll(){
        return colorRepository.findAll();
    }

    //GET only active color

    public List<Color> getAllActive() {
        List<Color> todas = colorRepository.findAll();
        List<Color> activas = new ArrayList<>();
    
        for (Color talla : todas) {
            if (talla.isActivo()) {
                activas.add(talla);
            }
           }
           return activas;
        
        }

    
    public Color getById(Integer id){
        
        Optional<Color> optional = colorRepository.findById(id);

        if(optional.isPresent()){
            return optional.get();
        }else{
            return null;
        }   
    }

    // this hibernate choose from update or save

    public Color save(Color color){
        return colorRepository.save(color);
    }

    public void deactivate(Integer id) {
        Optional<Color> colorOpt = colorRepository.findById(id);  // 1. Busca en BD
        
        if (colorOpt.isPresent()) {           // 2. ¿Existe?
            Color color = colorOpt.get();     // 3. Obtén el objeto
            color.setActivo(false);           // 4. Cambia a false
            colorRepository.save(color);      // 5. Guarda en BD
        }
    }

     // Activar
     public void activate(Integer id) {
        Optional<Color> colorOpt = colorRepository.findById(id);
        if (colorOpt.isPresent()) {
            Color color = colorOpt.get();
            color.setActivo(true);
            colorRepository.save(color);
        }
    }
    
    // Delete by ID para dejarlo en desarollo no en produccion
    public void delete(Integer id) {
        // Nota: Esto falla si hay relaciones (ej: variantes usando esta talla)
        // Más adelante manejaremos esto con validaciones
        colorRepository.deleteById(id);
    }



        
    
    
}
