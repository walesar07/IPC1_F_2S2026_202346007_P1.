
package com.mycompany.proyecto1.controllers;

import com.mycompany.proyecto1.models.EspacioRefugio;


public class UbicacionControllers {
    //Matriz de objetos de tipo espaciorefugio
    private EspacioRefugio[][] matrizRefugio;
    private int filas;
    private int columnas;
    
    public UbicacionControllers(int filasAreas, int columnasJaulas){
        this.filas = filasAreas;
        this.columnas = columnasJaulas;
        this.matrizRefugio = new EspacioRefugio[filasAreas][columnasJaulas];
        //Inicializamos cada celda de la matriz con su propio objeto EspacioRefugio.
        for (int i=0; i<filasAreas; i++){
            for (int j=0; j<columnasJaulas; j++){
                matrizRefugio[i][j]= new EspacioRefugio(i,j);
            }
        }
    
    }
    //Asignar un animal a un espacio de la matriz
    public boolean asignarUbicacion(int fila, int columna, String codigoAnimal){
        if (fila<0 || fila>= filas || columna<0 || columna >= columnas){
            return false;//Posici[on fuera de rango
        }
        EspacioRefugio espacio = matrizRefugio[fila][columna];
        if (espacio.isOcupado() || !espacio.getEstadoEspacio().equals("Disponible")){
            return false;//Espacio no disponible
        }
        espacio.setCodigoAnimal(codigoAnimal);
        return true;
    }
    //Liberar una posici[on
    public boolean liberarUbicacion(int fila, int columna){
        if (fila >= 0 && fila< filas && columna >= 0 && columna<  columnas){
            matrizRefugio[fila][columna].setCodigoAnimal(null);
            return true;
        }
        return false;
    }
    public EspacioRefugio[][] getMatrizRefugio(){
        return matrizRefugio;
    }
    
    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
        
}

