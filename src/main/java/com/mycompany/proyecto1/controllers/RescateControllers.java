
package com.mycompany.proyecto1.controllers;

import com.mycompany.proyecto1.models.Rescate;


public class RescateControllers {
        private Rescate[] rescates;
        private int contador;
        
        public RescateControllers(int capacidadMaxima){
            this.rescates = new Rescate[capacidadMaxima];
            this.contador = 0;
        }
        
        //Registrar rescate
        public boolean agregarRescate (Rescate rescate){
            if(rescate == null || rescate.getIdRescate() == null){
                return false;
            }
            if (contador >= rescates.length){
                return false;
            }
            if (buscarPorId(rescate.getIdRescate()) != null){
                return false;
                
            }
            rescates[contador] = rescate;
            contador ++;
            return true;
        }
        //Busqueda por ID rescate
        
        public Rescate buscarPorId(String idRescate){
            if (idRescate == null) return null;
            
            for (int i=0; i<contador; i++){
                if(rescates[i].getIdRescate().equalsIgnoreCase(idRescate)){
                   return rescates[i];
            }
        }
        return null;
    }
    //Marcar rescate como Atendido
    public boolean atenderRescate(String idRescate){
        Rescate r= buscarPorId(idRescate);
        if(r != null){
            r.setEstado("Atendido");
            return true;
        }
        return false;
    }
    public Rescate[] obtenerRescates(){
        Rescate[] resultado = new Rescate[contador];
        for(int i= 0 ; i< contador; i++){
            resultado[i]= rescates[i];
        }
        return resultado;
    }   
}


