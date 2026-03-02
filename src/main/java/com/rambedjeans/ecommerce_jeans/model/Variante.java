package com.rambedjeans.ecommerce_jeans.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Variante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVariante;
    
    @Column(name = "idReferencia", nullable = false)
    private String idReferencia;
    
    @Column(name = "idTalla", nullable = false)
    private Integer idTalla;
    
    @Column(name = "idColor", nullable = false)
    private Integer idColor;
    
    @Column(unique = true, length = 100)
    private String sku;
    
    @Column(nullable = false)
    private Boolean activo = true;

    public Variante() {
    }

    public Variante(Integer idVariante, String idReferencia, Integer idTalla, Integer idColor, String sku,
            Boolean activo) {
        this.idVariante = idVariante;
        this.idReferencia = idReferencia;
        this.idTalla = idTalla;
        this.idColor = idColor;
        this.sku = sku;
        this.activo = true;
    }

    public Integer getIdVariante() {
        return idVariante;
    }

    public void setIdVariante(Integer idVariante) {
        this.idVariante = idVariante;
    }

    public String getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(String idReferencia) {
        this.idReferencia = idReferencia;
    }

    public Integer getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(Integer idTalla) {
        this.idTalla = idTalla;
    }

    public Integer getIdColor() {
        return idColor;
    }

    public void setIdColor(Integer idColor) {
        this.idColor = idColor;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    
    
    
}
