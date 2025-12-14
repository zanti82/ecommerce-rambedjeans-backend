package com.rambedjeans.ecommerce_jeans.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tallas")
public class Tallas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ← AUTO_INCREMENT
    private Integer idTalla;  // Integer, no String
    
    @Column(unique = true, nullable = false)
    private String nombreTalla;
    
    // Constructor vacío (OBLIGATORIO)
    public Tallas() {
    }
    
    // Constructor con parámetros (sin idTalla, porque se genera auto)
    public Tallas(String nombreTalla) {
        this.nombreTalla = nombreTalla;
    }
    
    // Getters y Setters
    public Integer getIdTalla() {
        return idTalla;
    }

    // NO pongas setter para idTalla, JPA lo maneja automáticamente
    // Si pones setter, podrías romper el AUTO_INCREMENT

    public String getNombreTalla() {
        return nombreTalla;
    }

    public void setNombreTalla(String nombreTalla) {
        this.nombreTalla = nombreTalla;
    }
    
    @Override
    public String toString() {
        return "Talla{" +
                "idTalla=" + idTalla +
                ", nombreTalla='" + nombreTalla + '\'' +
                '}';
    }
}
