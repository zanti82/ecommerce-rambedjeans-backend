package com.rambedjeans.ecommerce_jeans.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "variantes")
public class Variante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  
    private Integer idVariante;

    // 🔹 RELACIONES

    @ManyToOne
    @JoinColumn(name = "idReferencia", nullable = false)
    private Referencia referencia;

    @ManyToOne
    @JoinColumn(name = "idColor", nullable = false)
    private Color color;

    @ManyToOne
    @JoinColumn(name = "idTalla", nullable = false)
    private Talla talla;

    // 🔹 CAMPOS

    @Column(nullable = false)
    private Integer stock;

    @Column(unique = true)
    private String sku;

    @Column(nullable = false)
    private Boolean activo;

    // 🔹 Constructor vacío (obligatorio)
    public Variante() {}

    // 🔥 Constructor limpio (como Gasto)
    public Variante(Referencia referencia, Color color, Talla talla, Integer stock, String sku, Boolean activo) {
        this.referencia = referencia;
        this.color = color;
        this.talla = talla;
        this.stock = stock;
        this.sku = sku;
        this.activo = activo;
    }

    public Integer getId() {
        return idVariante;
    }

    public void setId(Integer id) {
        this.idVariante = id;
    }

    public Referencia getReferencia() {
        return referencia;
    }

    public void setReferencia(Referencia referencia) {
        this.referencia = referencia;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Talla getTalla() {
        return talla;
    }

    public void setTalla(Talla talla) {
        this.talla = talla;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    // getters y setters

    
}