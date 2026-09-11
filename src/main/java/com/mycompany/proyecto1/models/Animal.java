
package com.mycompany.proyecto1.models;


public class Animal {
    
    private String codigo;
    private String nombre;
    private String especie;
    private String estado;
    private String estadoAdopcion;
    private boolean activo;
    private int edad;
    
    //constructor principal
    public Animal(String codigo, String nombre, String especie, String estado,
    String estadoAdopcion, boolean activo, int edad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.especie = especie;
        this.estado = estado;
        this.estadoAdopcion = estadoAdopcion;
        this.activo = activo; //todo animal registrado inicia activo por defecto.
        this.edad = edad;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
        public String getEstadoAdopcion() {
        return estadoAdopcion;
    }

    public void setEstadoAdopcion(String estadoAdopcion) {
        this.estadoAdopcion = estadoAdopcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    
}
