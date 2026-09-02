
package com.mycompany.proyecto1.models;


public class Animal {
    
    private int codigo;
    private String nombre;
    private String epecie;
    private String estado;

    public Animal(int codigo, String nombre, String epecie, String estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.epecie = epecie;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEpecie() {
        return epecie;
    }

    public void setEpecie(String epecie) {
        this.epecie = epecie;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
