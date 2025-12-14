package com.rambedjeans.ecommerce_jeans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rambedjeans.ecommerce_jeans.model.Color;


@Repository
public interface ColorRepository extends JpaRepository<Color, Integer>{

    // Spring Data JPA genera automáticamente la implementación 
    //hace el crud: save(), findAll(), findById(), deleteById(), etc.

    
}
