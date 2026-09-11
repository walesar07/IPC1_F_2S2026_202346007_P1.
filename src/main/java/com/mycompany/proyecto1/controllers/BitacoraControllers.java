
package com.mycompany.proyecto1.controllers;

import com.mycompany.proyecto1.models.Bitacora;

public class BitacoraControllers {
    private Bitacora[] registros;
    private int contador;
    
    public BitacoraControllers (int capacidadMaxima){
        this.registros = new Bitacora[capacidadMaxima];
        this.contador = 0;
    }
     //Registrar nueva accion (retorna boolean para confirmar si se guardo)
    public boolean registrarAccion(String usuario, String accion, String fechaHora){
        if(contador < registros.length){
            registros[contador] = new Bitacora(usuario, accion, fechaHora);
            contador ++;
            return true;            
        }
        return false; //Arreglo lleno
    }
    
    public Bitacora[] obtenerRegistro(){
        Bitacora[] resultado = new Bitacora[contador];
        for(int i=0; i< contador; i++){
            resultado[i] = registros[i];
        }
        return resultado;
    }
}
