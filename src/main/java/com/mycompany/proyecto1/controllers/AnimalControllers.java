package com.mycompany.proyecto1.controllers;

import com.mycompany.proyecto1.models.Animal;

public class AnimalControllers {
    
    private Animal[] animales;
    private int contador; // lleva el control de cuántos animales hay guardados.

    public AnimalControllers(int capacidadMaxima){
        //inicializamos el arreglo con un tamaño fijo predefinido
        this.animales = new Animal[capacidadMaxima];
        this.contador = 0;
    }

    //Método para agregar un animal validando espacio y duplicados
    public boolean agregarAnimal(Animal nuevo) {
        //validar que el objeto recibido no sea nulo
        if (nuevo == null || nuevo.getCodigo() == null || nuevo.getCodigo().trim().isEmpty()){
            return false;
        }
        if (contador >= animales.length){
            return false;//areglo lleno
        }
        // se revisan todos los codigos (activos e inactivos) para evitar duplicados
        if (existeCodigoEnArreglo(nuevo.getCodigo())){
            return false; // ya existe un animal con ese código
        }
        animales[contador] = nuevo;
        contador++;
        
        return true;
    }

    //Busqueda lineal por código (solo busca animales activos)
    public Animal buscarPorCodigo(String codigo){
        
        if (codigo == null) {
        return null;
        }

        for (int i = 0; i < contador; i++){
            
            
            if (animales[i] != null
                    && animales[i].isActivo() 
                    && animales[i].getCodigo().equalsIgnoreCase(codigo)){
                
                return animales[i];
            }
        }
        return null; //no encontrado
    }

    //Método auxiliar para revisar duplicados reales(sin importar si están activos o no)
    private boolean existeCodigoEnArreglo(String codigo){
        
        for (int i = 0; i < contador; i++){
            
            if (animales[i] != null 
                    && animales[i].getCodigo() != null
                    && animales[i].getCodigo().equalsIgnoreCase(codigo)){
                
                return true;
            }
        }
        return false;
    }

    //Eliminación lógica
    public boolean eliminarAnimal(String codigo){
        
        Animal a = buscarPorCodigo(codigo);
        
        if (a != null) {
            a.setActivo(false);//Eliminación lógica
            return true;
        }
        return false;
    }
    //Retorna un arreglo estático dimensionado exactamente con los animales activos
    public Animal[] obtenerAnimalesActivos(){
        int activosCount = 0;
        //1.Primer recorrido: contar activos
        for ( int i=0; i<contador; i++){
            if (animales[i] != null && animales[i].isActivo()){
                activosCount++;
            }
        }
        //2. Crear arreglo est[atico del tamano exacto necesario
        Animal[] activos = new Animal[activosCount];
        int index = 0;
        //3. Segundo recorrido: copiar referencias
        for (int i=0; i<contador; i++){
            if (animales[i] != null && animales[i].isActivo()){
                activos[index]= animales[i];
                index++;
            }
                
        }
        return activos;
            
    }
   // Retorna todos los registros almacenados hasta el momento (activos e inactivos)
   public Animal[] obtenerTodosLosAnimales() {
        Animal[] registrados = new Animal[contador];
        for (int i = 0; i < contador; i++) {
            registrados[i] = animales[i];
        }
        return registrados;
    }

    public int getContador() {
        return contador;
    }

    public int getCapacidadMaxima() {
        return animales.length;
    }
}