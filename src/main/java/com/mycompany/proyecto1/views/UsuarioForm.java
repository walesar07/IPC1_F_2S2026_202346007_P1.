package com.mycompany.proyecto1.views;

import com.mycompany.proyecto1.controllers.UsuarioControllers;
import com.mycompany.proyecto1.models.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioForm extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JComboBox<String> cbRol;
    private JButton btnRegistrar;
    private JButton btnLimpiar;
    private JTable tblUsuarios;
    private JButton btnVolver;
    private DefaultTableModel tableModel;

    private UsuarioControllers usuarioController;

    public UsuarioForm(UsuarioControllers usuarioController) {
        this.usuarioController = usuarioController;

        configurarVentana();
        inicializarComponentes();
        cargarTablaUsuarios();
    }

    private void configurarVentana() {
        setTitle("Gestión de Usuarios Auxiliares");
        setSize(550, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Título del Formulario
        JLabel lblTitulo = new JLabel("REGISTRO DE USUARIOS AUXILIARES", SwingConstants.CENTER);
        lblTitulo.setBounds(20, 15, 500, 25);
        panel.add(lblTitulo);

        // Campo: Usuario
        JLabel lblUsuario = new JLabel("Nombre de Usuario:");
        lblUsuario.setBounds(30, 55, 140, 25);
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(180, 55, 180, 25);
        panel.add(txtUsuario);

        // Campo: Contraseña
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(30, 90, 140, 25);
        panel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(180, 90, 180, 25);
        panel.add(txtPassword);

        // Campo: Rol
        JLabel lblRol = new JLabel("Rol asignado:");
        lblRol.setBounds(30, 125, 140, 25);
        panel.add(lblRol);

        // Por especificación, desde este módulo se registran auxiliares
        cbRol = new JComboBox<>(new String[]{"USUARIO AUXILIAR", "ADMINISTRADOR"});
        cbRol.setBounds(180, 125, 180, 25);
        panel.add(cbRol);

        // Botón Guardar / Registrar
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(380, 55, 120, 30);
        panel.add(btnRegistrar);

        // Botón Limpiar
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(380, 95, 120, 30);
        panel.add(btnLimpiar);
        // Crear el botón de regreso
btnVolver = new JButton("Volver al Menú");

// Posición y tamaño: (X, Y, Ancho, Alto)
// Ajusta X e Y según la esquina inferior donde tengas espacio libre en ese panel
btnVolver.setBounds(30, 410, 150, 30); 

// Acción para cerrar únicamente la ventana actual
btnVolver.addActionListener(e -> dispose());

// Agregar al panel de la interfaz
panel.add(btnVolver);

        // Tabla de usuarios registrados
        tableModel = new DefaultTableModel(new Object[]{"Código", "Usuario", "Rol"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Bloquea edición directa en celdas
            }
        };

        tblUsuarios = new JTable(tableModel);
        JScrollPane scrollTable = new JScrollPane(tblUsuarios);
        scrollTable.setBounds(30, 170, 470, 180);
        panel.add(scrollTable);

        // --- Eventos ---
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarNuevoUsuario();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        add(panel);
    }

    private void registrarNuevoUsuario() {
        String user = txtUsuario.getText().trim();
        String pass = new String(txtPassword.getPassword()).trim();
        String rol = (String) cbRol.getSelectedItem();

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Todos los campos son obligatorios.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Usuario nuevoUsuario = new Usuario(user, pass, rol);
        boolean exito = usuarioController.registrarUsuario(nuevoUsuario);

        if (exito) {
            JOptionPane.showMessageDialog(this,
                    "Usuario registrado exitosamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            cargarTablaUsuarios();
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se pudo registrar el usuario. Verifique si el nombre ya existe o si el arreglo está lleno.",
                    "Error de Registro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarTablaUsuarios() {
        tableModel.setRowCount(0); // Limpia filas actuales

        for (Usuario u : UsuarioControllers.usuarios) {
            if (u != null) {
                tableModel.addRow(new Object[]{
                        u.getCodigo(),
                        u.getUsuario(),
                        u.getRol()
                });
            }
        }
    }

    private void limpiarCampos() {
        txtUsuario.setText("");
        txtPassword.setText("");
        cbRol.setSelectedIndex(0);
    }
}
