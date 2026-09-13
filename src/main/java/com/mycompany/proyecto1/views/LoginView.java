package com.mycompany.proyecto1.views;

import com.mycompany.proyecto1.controllers.AdoptanteControllers;
import com.mycompany.proyecto1.controllers.AnimalControllers;
import com.mycompany.proyecto1.controllers.BitacoraControllers;
import com.mycompany.proyecto1.controllers.RescateControllers;
import com.mycompany.proyecto1.controllers.SolicitudControllers;
import com.mycompany.proyecto1.controllers.UbicacionControllers;
import com.mycompany.proyecto1.controllers.UsuarioControllers;
import com.mycompany.proyecto1.models.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar;
    
    private UsuarioControllers usuarioController;
    private AnimalControllers animalController;
    private AdoptanteControllers adoptanteController;
    private SolicitudControllers solicitudController;
    private RescateControllers rescateController;
    private UbicacionControllers ubicacionController;
    private BitacoraControllers bitacoraController;

    public LoginView(UsuarioControllers usuarioController, AnimalControllers animalController,
                     AdoptanteControllers adoptanteController, SolicitudControllers solicitudController,
                     RescateControllers rescateController, UbicacionControllers ubicacionController,
                     BitacoraControllers bitacoraController) {
        this.usuarioController = usuarioController;
        this.animalController = animalController;
        this.adoptanteController = adoptanteController;
        this.solicitudController = solicitudController;
        this.rescateController = rescateController;
        this.ubicacionController = ubicacionController;
        this.bitacoraController = bitacoraController;
        
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Acceso al Sistema - Refugio");
        setSize(360, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("INICIO DE SESIÓN", SwingConstants.CENTER);
        lblTitulo.setBounds(50, 20, 250, 25);
        panel.add(lblTitulo);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(40, 65, 80, 25);
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(120, 65, 170, 25);
        panel.add(txtUsuario);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(40, 105, 80, 25);
        panel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 105, 170, 25);
        panel.add(txtPassword);

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(120, 155, 110, 30);
        panel.add(btnIngresar);

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarLogin();
            }
        });

        add(panel);
    }

    private void validarLogin() {
        String user = txtUsuario.getText().trim();
        String pass = new String(txtPassword.getPassword()).trim();

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Debe ingresar el usuario y la contraseña.", 
                "Campos Incompletos", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        Usuario usuarioLogueado = usuarioController.login(user, pass);

        if (usuarioLogueado != null) {
            JOptionPane.showMessageDialog(this, 
                "¡Bienvenido " + usuarioLogueado.getUsuario() + "!\nRol: " + usuarioLogueado.getRol());
            
            // Abre el MainFrame pasando los controladores compartidos
            MainFrame main = new MainFrame(
                usuarioLogueado, 
                animalController, 
                usuarioController, 
                adoptanteController, 
                solicitudController,
                rescateController,
                ubicacionController,
                bitacoraController
            );
            main.setVisible(true);
            
            this.dispose(); // Cierra el Login
        } else {
            JOptionPane.showMessageDialog(this, 
                "Credenciales incorrectas. Verifique e intente de nuevo.", 
                "Error de Autenticación", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}