package com.rambedjeans.ecommerce_jeans.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DetalleDTO {

    @NotNull
    private Integer varianteId;
    
    @NotNull
    @Min(1)
    private Integer cantidad;

    //constructor
    public DetalleDTO(Integer varianteId, Integer cantidad) {
        this.varianteId = varianteId;
        this.cantidad = cantidad;
    }

     //constructor
    public DetalleDTO() {
    }

     //geters y seters

    public Integer getVarianteId() {
        return varianteId;
    }

    public void setVarianteId(Integer varianteId) {
        this.varianteId = varianteId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    
}
