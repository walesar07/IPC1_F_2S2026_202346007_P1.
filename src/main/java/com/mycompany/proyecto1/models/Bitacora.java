

package com.mycompany.proyecto1.models;


public class Bitacora {
    private String usuario;
    private String accion;
    private String fechaHora;

    public Bitacora(String usuario, String accion, String fechaHora) {
        this.usuario = usuario;
        this.accion = accion;
        this.fechaHora = fechaHora;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    
}
