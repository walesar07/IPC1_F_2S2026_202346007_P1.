package com.mycompany.proyecto1;

import com.mycompany.proyecto1.controllers.*;
import com.mycompany.proyecto1.views.LoginView;

public class Proyecto1 {

    public static void main(String[] args) {
        // Inicialización de TODOS los controladores del proyecto
        UsuarioControllers usuarioController = new UsuarioControllers();
        AnimalControllers animalController = new AnimalControllers(100);
        AdoptanteControllers adoptanteController = new AdoptanteControllers(100);
        SolicitudControllers solicitudController = new SolicitudControllers(100);
        RescateControllers rescateController = new RescateControllers(100);
        UbicacionControllers ubicacionController = new UbicacionControllers(10,10);
        BitacoraControllers bitacoraController = new BitacoraControllers(100);

        // Iniciar la app desde el Login pasando la bolsa completa de controladores
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginView login = new LoginView(
                usuarioController, 
                animalController, 
                adoptanteController, 
                solicitudController,
                rescateController,
                ubicacionController,
                bitacoraController
            );
            login.setVisible(true);
        });
    }
}