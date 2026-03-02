package com.rambedjeans.ecommerce_jeans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rambedjeans.ecommerce_jeans.model.Variante;

public interface VarianteRepository extends JpaRepository <Variante, Integer> {

    //aca usa  Spring Data JPA genera automáticamente la implementación 
    //hace el crud: save(), findAll(), findById(), deleteById(), etc.
    
}
