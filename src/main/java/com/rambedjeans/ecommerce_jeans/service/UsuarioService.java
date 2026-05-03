package com.rambedjeans.ecommerce_jeans.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rambedjeans.ecommerce_jeans.model.Role;
import com.rambedjeans.ecommerce_jeans.model.Usuario;
import com.rambedjeans.ecommerce_jeans.repository.UsuarioRepositorio;

@Service
public class UsuarioService {

    private final UsuarioRepositorio usuarioRepositorio;
    private  final PasswordEncoder passwordEncoder;

    //constructo que inyecta la dependencia

    public UsuarioService(UsuarioRepositorio usuarioRepositorio, PasswordEncoder passwordEncoder) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    //USERS LIST

    public List<Usuario> getAll(){
        return usuarioRepositorio.findAll();
    }

    //USERS ACTIVE LIST

    public List<Usuario> getAllActive() {
    
    /* return usuarioRepositorio.findAll().stream()
            .filter(Usuario::isActivo).toList(); */
    
    List<Usuario> todos = usuarioRepositorio.findAll();
    List<Usuario> activos = new ArrayList<>();

    for(Usuario user : todos ){
        if(user.getActivo()){
            activos.add(user);
        }
    }
    return activos;
    }

    // GET USER BY ID

    public Usuario getById(Integer id) {
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

    //CREATE USER

    public Usuario save(Usuario usuario){

        //enciptamos el password 
        String passwordEncriptada = passwordEncoder.encode(usuario.getPassword());

        usuario.setPassword(passwordEncriptada);
        usuario.setFechaCreacion(LocalDateTime.now());
        usuario.setRol(Role.USER);
        usuario.setActivo(true);
        usuario.setUltimoAcceso(LocalDateTime.now());

        //cremos el user
        return usuarioRepositorio.save(usuario);
    }

    //UPDATE USER

    public Usuario update(Integer id, Usuario usuarioNuevo) {

        //Miramos si existe
        Usuario usuarioExistente = usuarioRepositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    

     
        // 🔥 AQUÍ va la lógica

        System.out.println(usuarioExistente);
        usuarioExistente.setActivo(usuarioNuevo.getActivo());
       // no va es unique usuarioExistente.setTipoDocumento(usuarioNuevo.getTipoDocumento());
        usuarioExistente.setNombre(usuarioNuevo.getNombre());
        usuarioExistente.setCorreo(usuarioNuevo.getCorreo());
        usuarioExistente.setTelefono(usuarioNuevo.getTelefono());
        usuarioExistente.setDireccion(usuarioNuevo.getDireccion());
        usuarioExistente.setIdentificacion (usuarioNuevo.getIdentificacion());
        usuarioExistente.setPassword(usuarioNuevo.getPassword());

        return usuarioRepositorio.save(usuarioExistente);
    }

     // USER DEACTIVATE (eliminación lógica)

     public Usuario deactivate(Integer id){
        
        Usuario usuarioActivo = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Variante no encontrada"));
    
        usuarioActivo.setActivo(false);
    
        return usuarioRepositorio.save(usuarioActivo);
    }

    /*  public void deactivate(String id){
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        
        usuario.ifPresent (ref -> {
            ref.setActivo(false);
            usuarioRepositorio.save(ref);
        });
    } */

    public Usuario activate(Integer id) {

        Usuario usuarioInactivo = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Variante no encontrada"));
    
        usuarioInactivo.setActivo(true);
    
        return usuarioRepositorio.save(usuarioInactivo);
    }    

    //USER DELETE ONLY IN DEVELOP
    public void delete(Integer id) {
        usuarioRepositorio.deleteById(id);
    }

    public long countActivas() {

        List<Usuario> todos = usuarioRepositorio.findAll();
        int contador = 0;

        for(Usuario user : todos ){
            if(user.getActivo()){
                contador++;
            }
        }
        return contador;
       /*  return usuarioRepositorio.findAll().stream()
                .filter(Usuario::isActivo).count();*/          
    } 
     
}
