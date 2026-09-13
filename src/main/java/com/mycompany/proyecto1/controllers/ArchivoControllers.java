

package com.mycompany.proyecto1.controllers;

import com.mycompany.proyecto1.models.Adoptante;
import com.mycompany.proyecto1.models.Animal;
import com.mycompany.proyecto1.models.EspacioRefugio;
import com.mycompany.proyecto1.models.Rescate;
import com.mycompany.proyecto1.models.Solicitud;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivoControllers {
    
    //1.Persistencia en archivos .csv
    //ANIMALES
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
    //ADOPTANTES
public static void guardarAdoptantesCSV(Adoptante[] adoptantes, String rutaArchivo) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
        for (Adoptante ad : adoptantes) {
            if (ad != null && ad.isActivo()) {
                String linea = String.format("%s,%s,%s,%s,%s",
                        ad.getDpi(),
                        ad.getNombreCompleto(),
                        ad.getTelefono(),
                        ad.getDireccion(),
                        ad.getCorreo());
                bw.write(linea);
                bw.newLine();
            }
        }
    } catch (IOException e) {
        System.err.println("Error al guardar adoptantes: " + e.getMessage());
    }
    }
    
        public static void cargarAdoptantesCSV(AdoptanteControllers adoptanteController, String rutaArchivo) {
        File file = new File(rutaArchivo);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(",");
                if (datos.length >= 5) {
                    Adoptante ad = new Adoptante(datos[0], datos[1], datos[2], datos[3], datos[4],true);
                    adoptanteController.agregarAdoptante(ad);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar adoptantes: " + e.getMessage());
        }
    }
    //SOLICITUDES
        public static void guardarSolicitudesCSV(Solicitud[] solicitudes, String rutaArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Solicitud s : solicitudes) {
                if (s != null) {
                    String linea = String.format("%s,%s,%s,%s,%s",
                            s.getIdSolicitud(),
                            s.getDpiAdoptante(),
                            s.getCodigoAnimal(),
                            s.getFecha(),
                            s.getEstado());
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error al guardar solicitudes: " + e.getMessage());
        }
    }

public static void cargarSolicitudesCSV(SolicitudControllers solicitudController, String rutaArchivo) {
    File file = new File(rutaArchivo);
    if (!file.exists()) return;

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
            String[] datos = linea.split(",");
            
            if (datos.length == 5) {
                // Formato nuevo: id, dpi, animal, fecha, estado
                Solicitud s = new Solicitud(datos[0], datos[1], datos[2], datos[3], datos[4]);
                solicitudController.agregarSolicitud(s);
            } else if (datos.length == 4) {
                // Formato antiguo por si quedaron registros sin fecha: id, dpi, animal, estado
                Solicitud s = new Solicitud(datos[0], datos[1], datos[2], "Sin Fecha", datos[3]);
                solicitudController.agregarSolicitud(s);
            }
        }
    } catch (IOException e) {
        System.err.println("Error al cargar solicitudes: " + e.getMessage());
    }
}

    //RESCATES
    public static void guardarRescatesCSV(Rescate[] rescates, String rutaArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Rescate r : rescates) {
                if (r != null) {
                    String linea = String.format("%s,%s,%s,%s",
                            r.getIdRescate(),
                            r.getDescripcionUbicacion(),
                            r.getNivelUrgencia(),
                            r.getEstado());
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error al guardar rescates: " + e.getMessage());
        }
    }

    public static void cargarRescatesCSV(RescateControllers rescateController, String rutaArchivo) {
        File file = new File(rutaArchivo);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    Rescate r = new Rescate(datos[0], datos[1], datos[2], datos[3]);
                    rescateController.agregarRescate(r);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar rescates: " + e.getMessage());
        }
    }
    //2.Reportes: generar reporte HTML 
    private static String getEstiloCSS() {
        return "<style>\n" +
                "  body { font-family: Arial, sans-serif; margin: 30px; background-color: #f4f6f9; }\n" +
                "  h1 { color: #2c3e50; text-align: center; margin-bottom: 20px; }\n" +
                "  table { width: 100%; border-collapse: collapse; background: white; border-radius: 8px; overflow: hidden; box-shadow: 0 0 10px rgba(0,0,0,0.1); }\n" +
                "  th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }\n" +
                "  th { background-color: #3498db; color: white; font-weight: bold; }\n" +
                "  tr:nth-child(even) { background-color: #f9f9f9; }\n" +
                "  tr:hover { background-color: #f1f1f1; }\n" +
                "</style>\n";
    }
      //REPORTE ANIMALES
    public static void generarReporteHTMLAnimales(Animal[] animales, String rutaSalidaHTML) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n<html>\n<head>\n<meta charset=\"UTF-8\">\n")
            .append("<title>Reporte de Animales</title>\n")
            .append(getEstiloCSS())
            .append("</head>\n<body>\n")
            .append("<h1>Centro de Rescate Animal - Listado de Animales</h1>\n")
            .append("<table>\n<tr><th>Código</th><th>Nombre</th><th>Especie</th><th>Edad</th><th>Estado Salud</th><th>Estado Adopción</th></tr>\n");

        if (animales != null) {
            for (Animal a : animales) {
                if (a != null && a.isActivo()) {
                    html.append("<tr>")
                        .append("<td>").append(a.getCodigo()).append("</td>")
                        .append("<td>").append(a.getNombre()).append("</td>")
                        .append("<td>").append(a.getEspecie()).append("</td>")
                        .append("<td>").append(a.getEdad()).append("</td>")
                        .append("<td>").append(a.getEstado()).append("</td>")
                        .append("<td>").append(a.getEstadoAdopcion()).append("</td>")
                        .append("</tr>\n");
                }
            }
        }
        html.append("</table>\n</body>\n</html>");

        escribirArchivo(rutaSalidaHTML, html.toString());
    }
     //REPORTE SOLICITUDES
    public static void generarReporteHTMLSolicitudes(Solicitud[] solicitudes, String rutaSalidaHTML) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n<html>\n<head>\n<meta charset=\"UTF-8\">\n")
            .append("<title>Reporte de Solicitudes</title>\n")
            .append(getEstiloCSS())
            .append("</head>\n<body>\n")
            .append("<h1>Centro de Rescate - Solicitudes de Adopción</h1>\n")
            .append("<table>\n<tr><th>ID Solicitud</th><th>DPI Adoptante</th><th>Código Animal</th><th>Fecha</th><th>Estado</th></tr>\n");

        if (solicitudes != null) {
            for (Solicitud s : solicitudes) {
                if (s != null) {
                   
html.append("<tr>");
html.append("<td>").append(s.getIdSolicitud()).append("</td>");
html.append("<td>").append(s.getDpiAdoptante()).append("</td>");
html.append("<td>").append(s.getCodigoAnimal()).append("</td>");
html.append("<td>").append(s.getFecha() != null ? s.getFecha() : "N/A").append("</td>"); // O el campo de fecha real que manejes
html.append("<td>").append(s.getEstado()).append("</td>");
html.append("</tr>");
                }
            }
        }
        html.append("</table>\n</body>\n</html>");

        escribirArchivo(rutaSalidaHTML, html.toString());
    }
    //REPORTE UBICACIONES
    public static void generarReporteHTMLUbicaciones(EspacioRefugio[][] matriz, int filas, int cols, String rutaSalidaHTML) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n<html>\n<head>\n<meta charset=\"UTF-8\">\n")
            .append("<title>Reporte de Ocupación de Jaulas</title>\n")
            .append(getEstiloCSS())
            .append("</head>\n<body>\n")
            .append("<h1>Mapa de Ocupación de Jaulas y Áreas</h1>\n")
            .append("<table>\n<tr><th>Área / Jaula</th>");

        for (int j = 0; j < cols; j++) {
            html.append("<th>Jaula ").append(j).append("</th>");
        }
        html.append("</tr>\n");

        if (matriz != null) {
            for (int i = 0; i < filas; i++) {
                html.append("<tr><td><strong>Área ").append(i).append("</strong></td>");
                for (int j = 0; j < cols; j++) {
                    EspacioRefugio esp = matriz[i][j];
                    if (esp != null && esp.isOcupado()) {
                        html.append("<td style=\"background-color:#ffcccc; color:#a00000;\">Ocupado (").append(esp.getCodigoAnimal()).append(")</td>");
                    } else {
                        html.append("<td style=\"background-color:#d4edda; color:#155724;\">Disponible</td>");
                    }
                }
                html.append("</tr>\n");
            }
        }
        html.append("</table>\n</body>\n</html>");

        escribirArchivo(rutaSalidaHTML, html.toString());
    }
    //REPORTE HTML DE ADOPTANTES
public static void generarReporteHTMLAdoptantes(Adoptante[] adoptantes, String rutaSalidaHTML) {
    StringBuilder html = new StringBuilder();
    html.append("<!DOCTYPE html>\n<html>\n<head>\n<meta charset=\"UTF-8\">\n")
        .append("<title>Reporte de Adoptantes</title>\n")
        .append(getEstiloCSS())
        .append("</head>\n<body>\n")
        .append("<h1>Centro de Rescate - Listado de Adoptantes Registrados</h1>\n")
        .append("<table>\n<tr><th>DPI</th><th>Nombre Completo</th><th>Teléfono</th><th>Dirección</th><th>Correo</th></tr>\n");

    if (adoptantes != null) {
        for (Adoptante ad : adoptantes) {
            if (ad != null && ad.isActivo()) {
                html.append("<tr>")
                    .append("<td>").append(ad.getDpi()).append("</td>")
                    .append("<td>").append(ad.getNombreCompleto()).append("</td>")
                    .append("<td>").append(ad.getTelefono()).append("</td>")
                    .append("<td>").append(ad.getDireccion()).append("</td>")
                    .append("<td>").append(ad.getCorreo()).append("</td>")
                    .append("</tr>\n");
            }
        }
    }
    html.append("</table>\n</body>\n</html>");

    escribirArchivo(rutaSalidaHTML, html.toString());
}

    //REPORTE HTML DE RESCATES URGENTES
    public static void generarReporteHTMLRescates(Rescate[] rescates, String rutaSalidaHTML) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n<html>\n<head>\n<meta charset=\"UTF-8\">\n")
            .append("<title>Reporte de Rescates Urgentes</title>\n")
            .append(getEstiloCSS())
            .append("</head>\n<body>\n")
            .append("<h1>Centro de Rescate - Control de Rescates Urgentes</h1>\n")
            .append("<table>\n<tr><th>ID Rescate</th><th>Ubicación / Descripción</th><th>Nivel Urgencia</th><th>Estado</th></tr>\n");

        if (rescates != null) {
            for (Rescate r : rescates) {
                if (r != null) {
                    html.append("<tr>")
                        .append("<td>").append(r.getIdRescate()).append("</td>")
                        .append("<td>").append(r.getDescripcionUbicacion()).append("</td>")
                        .append("<td>").append(r.getNivelUrgencia()).append("</td>")
                        .append("<td>").append(r.getEstado()).append("</td>")
                        .append("</tr>\n");
                }
            }
        }
        html.append("</table>\n</body>\n</html>");

        escribirArchivo(rutaSalidaHTML, html.toString());
    }

    private static void escribirArchivo(String ruta, String contenido) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write(contenido);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo " + ruta + ": " + e.getMessage());
        }
    }
}
