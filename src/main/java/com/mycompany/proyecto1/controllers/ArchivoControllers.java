

package com.mycompany.proyecto1.controllers;

import com.mycompany.proyecto1.models.Animal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivoControllers {
    
    //1.Persistencia: guardar animales en un archivo .csv
    public static void guardarAnimalCSV(Animal[] animales, String rutaArchivo){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))){
            for (Animal a: animales){
                if (a != null && a.isActivo()){
                    //Formato CSV: codigo, nombre, especie, edad,estadoSalud, estadoAdopcion
                    String linea = String.format("%s,%s,%s,%d,%s,%s",
                            a.getCodigo(),
                            a.getNombre(),
                            a.getEspecie(),
                            a.getEdad(),
                            a.getEstado(),
                            a.getEstadoAdopcion());
                    bw.write(linea);
                    bw.newLine();                 
                
                }
                
            }
        } catch (IOException e){
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
             
    }
    //2.Persistenia: cargar animales desde un archivo .csv
    public static void cargarAnimalesCSV(AnimalControllers animalControlers, String rutaArchivo){
        File file = new File(rutaArchivo);
        if (!file.exists()) return;//si el archivo no existe, no hace nada
        
        try (BufferedReader br= new BufferedReader(new FileReader(file))){
            String linea;
            while ((linea = br.readLine()) != null){
                if (linea.trim().isEmpty()) continue;
                
                String[] datos = linea.split(",");
                if (datos.length == 6) {
                    String codigo = datos[0];
                    String nombre = datos[1];
                    String especie = datos[2];
                    int edad = Integer.parseInt(datos[3]);
                    String salud = datos[4];
                    String adopcion = datos [5];
                    
                    Animal animal = new Animal(codigo, nombre, especie, salud, adopcion, true, edad);
                    animalControlers.agregarAnimal(animal);
                }
                
            }
        } catch (IOException | NumberFormatException e){
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }
    }
    //3.Reportes: generar reporte HTML de Animales
    public static void generarReporteHTMLAnimales(Animal[] animales, String rutaSalidaHTML){
        StringBuilder html = new StringBuilder();
        
             html.append("<!DOCTYPE html>\n")
                 .append("   <meta charset=\"UTF-8\">\n")
                 .append("   <title>Reporte de Animales - Centro de Rescate</title>\n")
                 .append("   <style>\n")
                 .append("       body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f6f9; }\n")
                 .append("       h1 { color: #2c3e50; text-align: center; }\n")
                 .append("       table { width: 100%; border-collapse: collapse; margin-top: 20px; background: white; }\n")
                 .append("       th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }\n")
                 .append("       th { background-color: #3498db; color: white; }\n")
                 .append("       tr:nth-child(even) { background-color: #f9f9f9; }\n")
                 .append("   </style>\n")
                 .append("</head>\n<body>\n")
                 .append("   <h1>Centro de Rescate Animal - Listado de Animales</h1>\n")
                 .append("   <table>\n")
                 .append("       <tr><th>Código</th><th>Nombre</th><th>Especie</th><th>Edad</th><th>Estado Salud</th><th>Estado Adopción</th></tr>\n");
         
        for (Animal a: animales){
            if(a != null && a.isActivo()){
                html.append("       <tr>")
                    .append("<td>").append(a.getCodigo()).append("</td>")
                    .append("<td>").append(a.getNombre()).append("</td>")
                    .append("<td>").append(a.getEspecie()).append("</td>")
                    .append("<td>").append(a.getEdad()).append("</td>")
                    .append("<td>").append(a.getEstado()).append("</td>")
                    .append("<td>").append(a.getEstadoAdopcion()).append("</td>")
                    .append("</tr>\n");
                
            }
        }
        html.append("   </table>\n</body>\n</html>");
        
        //Escribir la cadena HTML generada en un archivo .html
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaSalidaHTML))){
            bw.write(html.toString());
        } catch (IOException e){
            System.err.println ("Error al generar reporte HTML: " + e.getMessage());
        }
                
                
               
    }
    
}
