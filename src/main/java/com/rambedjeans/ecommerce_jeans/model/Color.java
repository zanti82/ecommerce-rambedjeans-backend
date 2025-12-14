package com.rambedjeans.ecommerce_jeans.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "colores")
public class Color {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idColor;
    
    @Column(unique = true, nullable = false)
    private String nombreColor;
    
    @Column(length = 7)  // #RRGGBB
    private String codigoHex;
    
    // Constructor vacío
    public Color() {
    }
    
    // Constructor con parámetros
    public Color(String nombreColor, String codigoHex) {
        this.nombreColor = nombreColor;
        this.codigoHex = codigoHex;
    }
    
    // Constructor sin codigoHex
    public Color(String nombreColor) {
        this.nombreColor = nombreColor;
    }
    
    // Getters y Setters
    public Integer getIdColor() {
        return idColor;
    }
    
    // NO setter para idColor

    public String getNombreColor() {
        return nombreColor;
    }

    public void setNombreColor(String nombreColor) {
        this.nombreColor = nombreColor;
    }

    public String getCodigoHex() {
        return codigoHex;
    }

    public void setCodigoHex(String codigoHex) {
        this.codigoHex = codigoHex;
    }
    
    @Override
    public String toString() {
        return "Color{" +
                "idColor=" + idColor +
                ", nombreColor='" + nombreColor + '\'' +
                ", codigoHex='" + codigoHex + '\'' +
                '}';
    }
}