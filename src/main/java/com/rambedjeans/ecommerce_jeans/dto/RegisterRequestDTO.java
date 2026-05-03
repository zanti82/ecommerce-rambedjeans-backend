package com.rambedjeans.ecommerce_jeans.dto;

import javax.management.relation.Role;

import com.rambedjeans.ecommerce_jeans.model.TipoDocumento;

public class RegisterRequestDTO {

    private String nombre;
    private String identificacion;
    private String correo;
    private String password;
    private TipoDocumento tipoDocumento;
    private String telefono;
    private String  direccion;
    // Constructores
    public RegisterRequestDTO() {
    }
    
    public RegisterRequestDTO(String nombre,String identificacion, String correo, String password, Role rol,
        TipoDocumento tipoDocumento, String telefono, String direccion ) {
        this.nombre=nombre;
        this.identificacion = identificacion;
        this.tipoDocumento = tipoDocumento;
        this.correo = correo;
        this.password = password;
        this.direccion = direccion;
        this.telefono = telefono;
        
    }
    
    // Getters y Setters
    public String getIdentificacion() {
        return identificacion;
    }
    
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
  

    
}
