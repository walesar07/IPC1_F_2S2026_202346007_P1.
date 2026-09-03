

package com.mycompany.proyecto1.models;


public class Solicitud {
    private String idSolicitud;
    private String dpiAdoptante;
    private String codigoAnimal;
    private String fecha;
    private String estado; //Pendiente, aprobado, rechazada.

    public Solicitud(String idSolicitud, String dpiAdoptante, String codigoAnimal, String fecha, String estado) {
        this.idSolicitud = idSolicitud;
        this.dpiAdoptante = dpiAdoptante;
        this.codigoAnimal = codigoAnimal;
        this.fecha = fecha;
        this.estado = "Pendiente";// Por defecto ingresa como pendiente
    }

    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getDpiAdoptante() {
        return dpiAdoptante;
    }

    public void setDpiAdoptante(String dpiAdoptante) {
        this.dpiAdoptante = dpiAdoptante;
    }

    public String getCodigoAnimal() {
        return codigoAnimal;
    }

    public void setCodigoAnimal(String codigoAnimal) {
        this.codigoAnimal = codigoAnimal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    

}
    
            
    

