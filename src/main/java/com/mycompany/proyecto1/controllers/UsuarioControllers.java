

package com.mycompany.proyecto1.controllers;

import com.mycompany.proyecto1.models.Usuario;


public class UsuarioControllers {
    
    public static Usuario[] usuarios = new Usuario[100];
    
    //Constructor: carga los usuarios iniciales al instanciar el controlador
    public UsuarioControllers(){
        //Solo los agrega si el arreglo esta vacio para no duplicarlos
        if (usuarios[0] == null){
            registrarUsuario(new Usuario("admin", "1234", "ADMINISTRADOR"));
            registrarUsuario(new Usuario("auxiliar", "1234", "AUXILIAR"));
        }
    }
    
    public Usuario login (String usuario, String password){
        
        if (usuarios == null || usuario == null || password == null){
            return null;
        }
         String userLimpio = usuario.trim();
         String passLimpia = password.trim();
         
        if (userLimpio.isEmpty() || passLimpia.isEmpty()) {
        return null;
        }
        for (Usuario u: usuarios){
            if(u == null){
                continue;
            }
            String usuarioGuardado = u.getUsuario();
            String passwordGuardado = u.getPassword();
            
            if(usuarioGuardado == null || passwordGuardado == null){
                continue;                
            }
            
            if(usuarioGuardado.equalsIgnoreCase(userLimpio) && passwordGuardado.equals(passLimpia)){
                return u;
            }
        }
        return null;
    }
    
    public boolean registrarUsuario (Usuario usuario){
        if (usuarios == null || usuario == null ){
            return false;
        }
        
        if (usuario.getUsuario() == null || usuario.getPassword () == null){
            return false;
        }
        if (buscarPorUsuario(usuario.getUsuario())!= null){
            return false;//Usuario duplicado
        }      
        for(int i=0; i<usuarios.length; i++){
            if (usuarios[i]==null){
                //Asignacion automatica del codigo
                usuario.setCodigo (i+1);
                usuarios[i]= usuario;
                return true;
            }
        }
        return false;
    }
    
    public Usuario buscarPorUsuario(String usuario){
        if (usuarios == null || usuario == null){
            return null;
        }
        for (Usuario u: usuarios){
            if (u==null || u.getUsuario()== null){
                 continue;
            }
            if (u.getUsuario().equals(usuario)){
                return u;
            }
        }
        return null;
    }
}
