
package com.mycompany.proyecto1.controllers;

import com.mycompany.proyecto1.models.Adoptante;

public class AdoptanteControllers {
    private Adoptante[] adoptantes;
    private int contador;
    
    public AdoptanteControllers(int capacidadMaxima){
      
        this.adoptantes = new Adoptante[capacidadMaxima];
        this.contador = 0;
    }
    
    //agregar un nuevo adoptante
    public boolean agregarAdoptante(Adoptante adoptante){
        
        // Validacion basica de nulos para evitar NullPointerException
        if (adoptante == null || adoptante.getDpi() == null){
           return false;
        }
        if (contador>= adoptantes.length){
            return false;//arreglo lleno
        
        }
        if (buscarCualquieraPorDpi(adoptante.getDpi()) != null){
    return false; // DPI duplicado (existente activo o inactivo)
}
        adoptantes[contador]= adoptante;
        contador++;
        return true;
    
    }
    // Buscar en general ( para validar si existe previamente antes de agregar)
public Adoptante buscarCualquieraPorDpi(String dpi){
    if(dpi == null) return null;
    
    for (int i = 0; i < contador; i++){
        if (adoptantes[i].getDpi() != null && adoptantes[i].getDpi().equalsIgnoreCase(dpi)){
            return adoptantes[i];
        }
    }
    return null;
}
    
    
    
    //Modificar un adoptante existente
    public boolean editarAdoptante(String dpi, String nuevoNombre, String nuevoTelefono, String nuevaDireccion, String nuevoCorreo){
        Adoptante adoptante = buscarCualquieraPorDpi(dpi);
        
        if (adoptante != null){
            adoptante.setNombreCompleto(nuevoNombre);
            adoptante.setTelefono(nuevoTelefono);
            adoptante.setDireccion(nuevaDireccion);
            adoptante.setCorreo(nuevoCorreo);
            return true;
        }
        return false;
    }
        
        
        // Eliminación lógica de un adoptante por su DPI
    public boolean eliminarAdoptante(String dpi) {
        Adoptante adoptante = buscarCualquieraPorDpi(dpi);

        if (adoptante != null) {
            adoptante.setActivo(false); // Cambia el estado para ocultarlo
            return true;
        }
        return false;
    }
    // Retorna solo los adoptantes activos para las tablas de la interfaz
public Adoptante[] obtenerAdoptantesActivos(){
    int activos = 0;
    for (int i = 0; i < contador; i++){
        if (adoptantes[i].isActivo()){
            activos++;
        }
    }
    
    Adoptante[] resultado = new Adoptante[activos];
    int index = 0;
    for (int i = 0; i < contador; i++){
        if (adoptantes[i].isActivo()){
            resultado[index++] = adoptantes[i];
        }
    }
    return resultado;
}

// Retorna todos los adoptantes (útil para guardar en archivos/persistencia)
public Adoptante[] obtenerTodos(){
    Adoptante[] resultado = new Adoptante[contador];
    for (int i = 0; i < contador; i++){
        resultado[i] = adoptantes[i];
    }
    return resultado;
}
    
}
