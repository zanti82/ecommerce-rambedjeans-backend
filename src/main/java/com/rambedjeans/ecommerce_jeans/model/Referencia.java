package com.rambedjeans.ecommerce_jeans.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "referencia")
public class Referencia {

    @Id
    @Column(name = "idReferencia", length = 50)
    private String idReferencia;

    @Column(name = "nombreReferencia", length = 100)
    private String nombreReferencia;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "estiloReferencia", length = 50)    
    private String estiloReferencia;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal precioBase;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero; 

    private Boolean activo;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp 
    private LocalDateTime fechaCreacion;
    
    //**constructor vacio  */
    public Referencia() { }

    //**constructor */
  
    public Referencia(String idReferencia, String nombreReferencia, String descripcion, String estiloReferencia,
            BigDecimal precioBase, Genero genero, Boolean activo) {
        this.idReferencia = idReferencia;
        this.nombreReferencia = nombreReferencia;
        this.descripcion = descripcion;
        this.estiloReferencia = estiloReferencia;
        this.precioBase = precioBase;
        this.genero = genero;
        this.activo = true;
        
    }


    public Genero getGenero() {
        return genero;
    }


    public void setGenero(Genero genero) {
        this.genero = genero;
    }



    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }


    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


    public String getIdReferencia() {
        return idReferencia;
    }


    public void setIdReferencia(String  idReferencia) {
        this.idReferencia = idReferencia;
    }


    public String getNombreReferencia() {
        return nombreReferencia;
    }


    public void setNombreReferencia(String nombreReferencia) {
        this.nombreReferencia = nombreReferencia;
    }


    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getEstiloReferencia() {
        return estiloReferencia;
    }


    public void setEstiloReferencia(String estiloReferencia) {
        this.estiloReferencia = estiloReferencia;
    }


    public BigDecimal getPrecioBase() {
           return precioBase;
    }


    public void setPrecioBase(BigDecimal precioBase) {

        if (precioBase != null && precioBase.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }

        this.precioBase = precioBase;
    }




    public Boolean isActivo() {
        return activo;
    }


    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

     
}
