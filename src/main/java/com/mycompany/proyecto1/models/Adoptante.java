
package com.mycompany.proyecto1.models;

public class Adoptante{
    private String dpi;
    private String nombreCompleto;
    private String telefono;
    private String direccion;
    private String correo;
    private boolean activo;

    public Adoptante(String dpi, String nombreCompleto, String telefono, String direccion, String correo, boolean activo) {
        this.dpi = dpi;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.activo= activo;
    }
    public Adoptante(String dpi, String nombreCompleto, String telefono, String direccion, String correo) {
    this(dpi, nombreCompleto, telefono, direccion, correo, true);
}

    public String getDpi() {
        return dpi;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    // Método auxiliar para guardar en archivo CSV/TXT
public String toCSV() {
    return dpi + ";" + nombreCompleto + ";" + telefono + ";" + direccion + ";" + correo + ";" + activo;
}

// Método auxiliar para representación en consola o debug
@Override
public String toString() {
    return nombreCompleto + " (DPI: " + dpi + ")";
}

    

}