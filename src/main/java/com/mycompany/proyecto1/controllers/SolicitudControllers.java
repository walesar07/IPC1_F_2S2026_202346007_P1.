
package com.mycompany.proyecto1.controllers;

import com.mycompany.proyecto1.models.Solicitud;

public class SolicitudControllers {
    private Solicitud[] solicitudes;
    private int contador;
    
    public SolicitudControllers(int capacidadMaxima){
        this.solicitudes = new Solicitud[capacidadMaxima];
        this.contador = 0;
    }
    //Registrar una solicitud
    public boolean agregarSolicitud(Solicitud solicitud){
        if (contador >= solicitudes.length){
            return false;
        }
        if (buscarPorId(solicitud.getIdSolicitud()) != null) {
            return false; //Id duplicado
        }      
        solicitudes[contador] = solicitud;
        contador++;
        return true;
                     
    }
    //Busqueda por ID de solicitud
    public Solicitud buscarPorId(String idSolicitud){
        for (int i=0; i< contador; i++){
            if (solicitudes[i].getIdSolicitud().equalsIgnoreCase(idSolicitud)){
                return solicitudes[i];
            }
        }
        return null;
    }
    
    //Cambiar estado de la solicitud
    public boolean cambiarEStado(String idSolicitud, String nuevoEstado){
        Solicitud s = buscarPorId(idSolicitud);
        if(s != null){
            s.setEstado(nuevoEstado);
            return true;
        }
        return false;
    }
    //Obtener todas las solicitudes
    public Solicitud[] obtenerSolicitud() {
        Solicitud[] resultado = new Solicitud[contador];
        for (int i=0; i<contador; i++){
            resultado[i]= solicitudes[i];
            
        }
        return resultado;
    }   
}
