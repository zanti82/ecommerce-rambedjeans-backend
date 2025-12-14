package com.rambedjeans.ecommerce_jeans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rambedjeans.ecommerce_jeans.model.Referencia;

public interface ReferenciaRepository extends JpaRepository <Referencia, String>  {

     // Spring Data JPA genera automáticamente la implementación 
    //hace el crud: save(), findAll(), findById(), deleteById(), etc.

    
}
