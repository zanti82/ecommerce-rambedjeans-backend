package com.rambedjeans.ecommerce_jeans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rambedjeans.ecommerce_jeans.model.Color;

public interface TallasRepository extends JpaRepository<Color, Integer>{

    
    // Spring Data JPA genera automáticamente la implementación 
    //hace el crud: save(), findAll(), findById(), deleteById(), etc.
    
}
