package com.rambedjeans.ecommerce_jeans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rambedjeans.ecommerce_jeans.model.DetalleFactura;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Integer>{
    
}
