package com.rambedjeans.ecommerce_jeans.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rambedjeans.ecommerce_jeans.model.EstadoFactura;
import com.rambedjeans.ecommerce_jeans.model.EstadoPago;
import com.rambedjeans.ecommerce_jeans.model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

    //buscar por estado de anulado o activo
    List<Factura> findByEstado(EstadoFactura estado);

    //buscar por estado pago por pagar
    List<Factura> findByEstadoPago(EstadoPago estadoPago);
    
}
