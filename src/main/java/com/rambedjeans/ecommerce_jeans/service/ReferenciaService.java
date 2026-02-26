package com.rambedjeans.ecommerce_jeans.service;

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
        return referenciaRepository.findAll().stream()
                .filter(Referencia::isActivo)
                .toList();
    }

     // Search by ID
     public Optional<Referencia> getbyId(String id) {
        return referenciaRepository.findById(id);
    }

    public Referencia save(Referencia referencia){
        return referenciaRepository.save(referencia);
    }

    // Deactivate (eliminación lógica)

    public void deactivate(String id){
        Optional<Referencia> referencia = referenciaRepository.findById(id);
        
        referencia.ifPresent (ref -> {
            ref.setActivo(false);
            referenciaRepository.save(ref);
        });
    }

    public void delete(String id) {
        referenciaRepository.deleteById(id);
    }

    public long countActivas() {
        return referenciaRepository.findAll().stream()
                .filter(Referencia::isActivo)
                .count();
    }


}
