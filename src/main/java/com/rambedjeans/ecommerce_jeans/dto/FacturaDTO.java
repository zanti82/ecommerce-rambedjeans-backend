package com.rambedjeans.ecommerce_jeans.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.NonNull;

public class FacturaDTO {
    
    @NonNull
    private Integer usuarioId;

    @NotEmpty
    private List<DetalleDTO> detalles;

     //constructor
    public FacturaDTO(Integer usuarioId, List<DetalleDTO> detalles) {
        this.usuarioId = usuarioId;
        this.detalles = detalles;
    }

     //constructor
    public FacturaDTO() {
    }

     //getters and setters
    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<DetalleDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleDTO> detalles) {
        this.detalles = detalles;
    }
    
    
    
}
