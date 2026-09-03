

package com.mycompany.proyecto1.models;


public class Rescate {
    private String idRescate;
    private String descripcionUbicacion;
    private String nivelUrgencia; // Alta, media, baja.
    private String estado; //Pendiente, atendido.

    public Rescate(String idRescate, String descripcionUbicacion, String nivelUrgencia, String estado) {
        this.idRescate = idRescate;
        this.descripcionUbicacion = descripcionUbicacion;
        this.nivelUrgencia = nivelUrgencia;
        this.estado = "Pendiente";
    }

    public String getIdRescate() {
        return idRescate;
    }

    public void setIdRescate(String idRescate) {
        this.idRescate = idRescate;
    }

    public String getDescripcionUbicacion() {
        return descripcionUbicacion;
    }

    public void setDescripcionUbicacion(String descripcionUbicacion) {
        this.descripcionUbicacion = descripcionUbicacion;
    }

    public String getNivelUrgencia() {
        return nivelUrgencia;
    }

    public void setNivelUrgencia(String nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
    

