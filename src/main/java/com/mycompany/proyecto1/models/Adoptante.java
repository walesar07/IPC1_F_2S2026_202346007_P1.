
package com.mycompany.proyecto1.models;

public class Adoptante{
    private int dpi;
    private String nombreCompleto;
    private int telefono;
    private String direcci0n;

    public Adoptante(int dpi, String nombreCompleto, int telefono, String direcci0n) {
        this.dpi = dpi;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.direcci0n = direcci0n;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDirecci0n() {
        return direcci0n;
    }

    public void setDirecci0n(String direcci0n) {
        this.direcci0n = direcci0n;
    }
    
}
