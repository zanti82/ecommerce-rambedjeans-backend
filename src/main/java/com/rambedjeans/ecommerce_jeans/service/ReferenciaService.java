package com.rambedjeans.ecommerce_jeans.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.rambedjeans.ecommerce_jeans.model.Referencia;
import com.rambedjeans.ecommerce_jeans.repository.ReferenciaRepository;

@Service
public class ReferenciaService {

    private final ReferenciaRepository referenciaRepository;

    //constructor que inyecta la dependencia
    public ReferenciaService(ReferenciaRepository referenciaRepository) {
        this.referenciaRepository = referenciaRepository;
    }

    public List<Referencia> getAll(){
        return referenciaRepository.findAll();
    }

    public List<Referencia> getAllActive() {

        List<Referencia> todas = referenciaRepository.findAll();
        List<Referencia> activas = new ArrayList<>();
    
        for (Referencia ref : todas) {
            if (ref.isActivo()) {
                activas.add(ref);
            }
           }
           return activas;


        //forma rapida pro
       /*  return referenciaRepository.findAll().stream()
                .filter(Referencia::isActivo)
                .toList();
                */ 
    }

     // Search by ID
     public Referencia getbyId(String id) {
         Optional<Referencia> optional = referenciaRepository.findById(id);

        if(optional.isPresent()){
            return optional.get();
        }else{
            return null;
        }   
    }

    public Referencia save(Referencia referencia){
        return referenciaRepository.save(referencia);
    }

    // Deactivate (eliminación lógica)

    public Referencia deactivate(String id){

        
        Optional<Referencia> referenciaOpt = referenciaRepository.findById(id);
        
        if (referenciaOpt.isPresent()) {
            Referencia referencia = referenciaOpt.get();
            referencia.setActivo(false);
            return referenciaRepository.save(referencia);
        }else{
            throw new RuntimeException("Referencia no encontrada");
        }
        
    }

    public void activate(String id){
        Optional<Referencia> referenciaOpt = referenciaRepository.findById(id);
        
        if (referenciaOpt.isPresent()) {
            Referencia referencia = referenciaOpt.get();
            referencia.setActivo(true);
            referenciaRepository.save(referencia);
        }
    }
    //esto en desarrollo, en produccion no va para evitar daño de relaciones
    public void delete(String id) {
        referenciaRepository.deleteById(id);
    }

    public long countActivas() {
        return referenciaRepository.findAll().stream()
                .filter(Referencia::isActivo)
                .count();
    }


}
