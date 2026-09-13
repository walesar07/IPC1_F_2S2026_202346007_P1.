package com.mycompany.proyecto1.views;

import com.mycompany.proyecto1.controllers.*;
import com.mycompany.proyecto1.models.Usuario;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

    private Usuario usuarioLogueado;
    
    // Todos los controladores compartidos
    private AnimalControllers animalController;
    private UsuarioControllers usuarioController;
    private AdoptanteControllers adoptanteController;
    private SolicitudControllers solicitudController;
    private RescateControllers rescateController;
    private UbicacionControllers ubicacionController;
    private BitacoraControllers bitacoraController;

    // Componentes de la interfaz
    private JButton btnAnimales;
    private JButton btnAdoptantes;
    private JButton btnRescates;
    private JButton btnSolicitudes;
    private JButton btnUbicaciones;
    private JButton btnUsuarios;
    private JButton btnCerrarSesion;

    public MainFrame(Usuario usuarioLogueado, AnimalControllers animalController, 
                     UsuarioControllers usuarioController, AdoptanteControllers adoptanteController, 
                     SolicitudControllers solicitudController, RescateControllers rescateController,
                     UbicacionControllers ubicacionController, BitacoraControllers bitacoraController) {
        
        this.usuarioLogueado = usuarioLogueado;
        this.animalController = animalController;
        this.usuarioController = usuarioController;
        this.adoptanteController = adoptanteController;
        this.solicitudController = solicitudController;
        this.rescateController = rescateController;
        this.ubicacionController = ubicacionController;
        this.bitacoraController = bitacoraController;

        configurarVentana();
        inicializarComponentes();
        aplicarPermisos();
    }

    private void configurarVentana() {
        setTitle("Sistema de Gestión de Refugio - Menú Principal");
        setSize(450, 430); // Altura ajustada ya que hay un botón menos
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ArchivoControllers.guardarAnimalCSV(animalController.obtenerTodosLosAnimales(), "animales.csv");
            }
        });
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblBienvenida = new JLabel("Bienvenido: " + usuarioLogueado.getUsuario(), SwingConstants.CENTER);
        lblBienvenida.setBounds(50, 15, 350, 20);
        panel.add(lblBienvenida);

        JLabel lblRol = new JLabel("Rol: " + usuarioLogueado.getRol(), SwingConstants.CENTER);
        lblRol.setBounds(50, 35, 350, 20);
        panel.add(lblRol);

        // 1. Animales
        btnAnimales = new JButton("Gestión de Animales");
        btnAnimales.setBounds(100, 70, 250, 32);
        panel.add(btnAnimales);

        // 2. Adoptantes
        btnAdoptantes = new JButton("Gestión de Adoptantes");
        btnAdoptantes.setBounds(100, 110, 250, 32);
        panel.add(btnAdoptantes);

        // 3. Rescates
        btnRescates = new JButton("Registro de Rescates");
        btnRescates.setBounds(100, 150, 250, 32);
        panel.add(btnRescates);

        // 4. Solicitudes de Adopción
        btnSolicitudes = new JButton("Solicitudes de Adopción");
        btnSolicitudes.setBounds(100, 190, 250, 32);
        panel.add(btnSolicitudes);

        // 5. Ubicaciones / Matriz Refugio
        btnUbicaciones = new JButton("Ubicaciones y Refugio");
        btnUbicaciones.setBounds(100, 230, 250, 32);
        panel.add(btnUbicaciones);

        // 6. Usuarios Auxiliares
        btnUsuarios = new JButton("Gestión de Auxiliares");
        btnUsuarios.setBounds(100, 270, 250, 32);
        panel.add(btnUsuarios);

        // 7. Cerrar Sesión (Posición Y recalculada hacia arriba)
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(150, 320, 150, 30);
        panel.add(btnCerrarSesion);

        // --- Asignación de Vistas ---

        btnAnimales.addActionListener(e -> new AnimalForm(animalController).setVisible(true));
        
        btnAdoptantes.addActionListener(e -> new AdoptanteView(adoptanteController).setVisible(true));
        
        btnRescates.addActionListener(e -> new RescateView(rescateController).setVisible(true));
        
        btnSolicitudes.addActionListener(e -> new SolicitudView(solicitudController, animalController, adoptanteController).setVisible(true));
        
        btnUbicaciones.addActionListener(e -> new UbicacionView(ubicacionController, animalController).setVisible(true));
        
        btnUsuarios.addActionListener(e -> new UsuarioForm(usuarioController).setVisible(true));

        btnCerrarSesion.addActionListener(e -> {
            dispose();
            new LoginView(usuarioController, animalController, adoptanteController, solicitudController, rescateController, ubicacionController, bitacoraController).setVisible(true);
        });

        add(panel);
    }

    private void aplicarPermisos() {
        if (usuarioLogueado.getRol() != null && usuarioLogueado.getRol().equalsIgnoreCase("USUARIO AUXILIAR")) {
            btnUsuarios.setEnabled(false);
            btnUsuarios.setToolTipText("Opción reservada para Administradores.");
        }
    }
}