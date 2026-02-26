package com.rambedjeans.ecommerce_jeans.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.rambedjeans.ecommerce_jeans.model.Usuario;
import com.rambedjeans.ecommerce_jeans.repository.UsuarioRepositorio;

@Service
public class UsuarioService {

    public final UsuarioRepositorio usuarioRepositorio;

    //constructo que inyecta la dependencia

    public UsuarioService(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

     public List<Usuario> getAll(){
        return usuarioRepositorio.findAll();
    }

      public List<Usuario> getAllActive() {
       
        /* return usuarioRepositorio.findAll().stream()
                .filter(Usuario::isActivo).toList(); */
        
        List<Usuario> todos = usuarioRepositorio.findAll();
        List<Usuario> activos = new ArrayList<>();

        for(Usuario user : todos ){
            if(user.isActivo()){
                activos.add(user);
            }
        }
        
        return activos;
    
    
    }

    // Search by ID

    public Usuario getById(String id) {
        Optional<Usuario> optional = usuarioRepositorio.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    /*  public Optional<Usuario> getbyId(String id) {
        return usuarioRepositorio.findById(id);
    } */

    public Usuario save(Usuario usuario){
        return usuarioRepositorio.save(usuario);
    }

     // Deactivate (eliminación lógica)

     public void deactivate(String id){

        Optional<Usuario> optionalUsuario = usuarioRepositorio.findById(id);
    
        if (optionalUsuario.isPresent()) {
    
            Usuario usuario = optionalUsuario.get();
    
            usuario.setActivo(false);
    
            usuarioRepositorio.save(usuario);
        }
    }

    /*  public void deactivate(String id){
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        
        usuario.ifPresent (ref -> {
            ref.setActivo(false);
            usuarioRepositorio.save(ref);
        });
    } */

    public void delete(String id) {
        usuarioRepositorio.deleteById(id);
    }

    public long countActivas() {

        List<Usuario> todos = usuarioRepositorio.findAll();
        int contador = 0;

        for(Usuario user : todos ){
            if(user.isActivo()){
                contador++;
            }
        }

        return contador;


       /*  return usuarioRepositorio.findAll().stream()
                .filter(Usuario::isActivo).count();*/
                
    } 
    
    
}
