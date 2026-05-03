package com.rambedjeans.ecommerce_jeans.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "facturas")
public class Factura {

     
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 🔹 Relación con usuario (cliente)
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    @CreationTimestamp 
    private LocalDateTime fecha;

    @Column(nullable = false)
    private BigDecimal total;

    // 🔹 Relación con detalle
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    private List<DetalleFactura> detalles;

    @Enumerated(EnumType.STRING)
    private EstadoFactura estado;

    @Enumerated(EnumType.STRING)
    private EstadoPago estado_Pago;

    //constructor vacío
     public Factura() {
       
    }

     public Factura(Usuario usuario, BigDecimal total) {
        this.usuario = usuario;
        this.total = total;
        this.fecha = LocalDateTime.now();
        this.estado = EstadoFactura.ACTIVA; //Eeste por defecto no pasan por param
        this.estado_Pago = EstadoPago.PENDIENTE;//Eeste por defecto no pasan por param

     }

     public Integer getId() {
         return id;
     }

     public void setId(Integer id) {
         this.id = id;
     }

     public Usuario getUsuario() {
         return usuario;
     }

     public void setUsuario(Usuario usuario) {
         this.usuario = usuario;
     }

     public LocalDateTime getFecha() {
         return fecha;
     }

     public void setFecha(LocalDateTime fecha) {
         this.fecha = fecha;
     }

     public BigDecimal getTotal() {
         return total;
     }

     public void setTotal(BigDecimal total) {
         this.total = total;
     }

     public List<DetalleFactura> getDetalles() {
         return detalles;
     }

     public void setDetalles(List<DetalleFactura> detalles) {
         this.detalles = detalles;
     }

     public EstadoFactura getEstado() {
         return estado;
     }

     public void setEstado(EstadoFactura estado) {
         this.estado = estado;
     }

     public EstadoPago getEstadoPago() {
         return estado_Pago;
     }

     public void setEstadoPago(EstadoPago estadoPago) {
         this.estado_Pago = estadoPago;
     }

     
     
  
    
}
