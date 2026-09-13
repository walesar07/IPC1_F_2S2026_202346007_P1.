

package com.mycompany.proyecto1.models;



public class EspacioRefugio {
    private int fila;
    private int columna;
    private String codigoAnimal;//null si esta libre
    private boolean ocupado;
    private String estadoEspacio;//Disponible, ocupado, en mantenimiento.            

    public EspacioRefugio(int fila, int columna, String codigoAnimal, boolean ocupado, String estadoEspacio) {
        this.fila = fila;
        this.columna = columna;
        this.codigoAnimal = codigoAnimal;
        this.ocupado = ocupado;
        this.estadoEspacio = estadoEspacio;
    }

   // Constructor simplificado para inicializar libre
    public EspacioRefugio(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.codigoAnimal = null;
        this.ocupado = false;
        this.estadoEspacio = "Disponible";
    }
    
    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getCodigoAnimal() {
        return codigoAnimal;
    }

    public void setCodigoAnimal(String codigoAnimal) {
        this.codigoAnimal = codigoAnimal;
        if (codigoAnimal != null && !codigoAnimal.isEmpty()) {
            this.ocupado = true;
            this.estadoEspacio = "Ocupado";
        } else {
            this.ocupado = false;
            this.estadoEspacio = "Disponible";
        }
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public String getEstadoEspacio() {
        return estadoEspacio;
    }

    public void setEstadoEspacio(String estadoEspacio) {
        this.estadoEspacio = estadoEspacio;
    }
    

}