
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
        if (buscarPorDpi(adoptante.getDpi())!= null){
            return false; //dpi duplicado
        }
        adoptantes[contador]= adoptante;
        contador++;
        return true;
    }
    //Busqueda por dpi
    public Adoptante buscarPorDpi(String dpi){
        if(dpi == null)return null;
        
        for (int i=0; i< contador; i++){
            if (adoptantes[i].getDpi()!= null && adoptantes[i].getDpi().equalsIgnoreCase(dpi)){
                return adoptantes[i];
            }
        }
        return null;
    }
    //Modificar un adoptante existente
    public boolean editarAdoptante(String dpi, String nuevoNombre, String nuevoTelefono, String nuevaDireccion){
        Adoptante adoptante = buscarPorDpi(dpi);
        
        if (adoptante != null){
            adoptante.setNombreCompleto(nuevoNombre);
            adoptante.setTelefono(nuevoTelefono);
            adoptante.setDireccion(nuevaDireccion);
            return true;
        }
        return false;
    }
    //obtener arreglo exacto de adoptantes para cargar en tablas Swing
    public Adoptante[] obtenerAdoptantes(){
        Adoptante[] resultado = new Adoptante[contador];
        for(int i=0; i<contador; i++){
            resultado[i]= adoptantes[i];
        }
        return resultado;
    }
}
