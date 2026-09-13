package com.mycompany.proyecto1.views;

import com.mycompany.proyecto1.controllers.AdoptanteControllers;
import com.mycompany.proyecto1.controllers.ArchivoControllers;
import com.mycompany.proyecto1.models.Adoptante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AdoptanteView extends JFrame {

    private JTextField txtDpi;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextField txtDireccion;

    private JButton btnGuardar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JTable tblAdoptantes;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;
    private JButton btnReporte;

    private AdoptanteControllers adoptanteController;

    public AdoptanteView(AdoptanteControllers adoptanteController) {
        this.adoptanteController = adoptanteController;

        configurarVentana();
        inicializarComponentes();
        cargarDatosTabla();
    }

    private void configurarVentana() {
        setTitle("Gestión de Adoptantes");
        setSize(680, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("REGISTRO DE ADOPTANTES", SwingConstants.CENTER);
        lblTitulo.setBounds(20, 15, 620, 25);
        panel.add(lblTitulo);

        // DPI
        JLabel lblDpi = new JLabel("DPI / ID:");
        lblDpi.setBounds(30, 55, 100, 25);
        panel.add(lblDpi);

        txtDpi = new JTextField();
        txtDpi.setBounds(120, 55, 170, 25);
        panel.add(txtDpi);

        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 90, 100, 25);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 90, 170, 25);
        panel.add(txtNombre);

        // Teléfono
        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(30, 125, 100, 25);
        panel.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(120, 125, 170, 25);
        panel.add(txtTelefono);

        // Correo
        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(320, 55, 100, 25);
        panel.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(410, 55, 200, 25);
        panel.add(txtCorreo);

        // Dirección
        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(320, 90, 100, 25);
        panel.add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(410, 90, 200, 25);
        panel.add(txtDireccion);

        // Botones
        btnGuardar = new JButton("Guardar / Editar");
        btnGuardar.setBounds(120, 165, 140, 30);
        panel.add(btnGuardar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(275, 165, 110, 30);
        panel.add(btnEliminar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(410, 165, 110, 30);
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

btnReporte = new JButton("Ver Reporte HTML");
btnReporte.setBounds(200, 410, 160, 30); // Ajusta las coordenadas (X, Y) según tu diseño
panel.add(btnReporte); // Asegúrate de que esté agregado al panel

btnReporte.addActionListener(e -> {
    // 1. Generamos el reporte HTML usando el controlador de archivos
    // (Verifica que 'obtenerTodosLosAdoptantes()' sea el nombre exacto del método en tu AdoptanteControllers)
    ArchivoControllers.generarReporteHTMLAdoptantes(
        adoptanteController.obtenerAdoptantesActivos(), 
        "reporte_adoptantes.html"
    );
    
// 2. Abrir automáticamente el archivo en el navegador predeterminado
    try {
        java.io.File archivoHtml = new java.io.File("reporte_adoptantes.html");
        if (archivoHtml.exists() && java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop.getDesktop().browse(archivoHtml.toURI());
        }
    } catch (java.io.IOException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "El reporte se creó, pero ocurrió un error al abrir el navegador.", 
            "Error", 
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }
});

panel.add(btnReporte);

        // Tabla
        modeloTabla = new DefaultTableModel(new Object[]{"DPI", "Nombre", "Teléfono", "Correo", "Dirección"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblAdoptantes = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tblAdoptantes);
        scroll.setBounds(30, 215, 610, 220);
        panel.add(scroll);

        // Listener para seleccionar elemento de la tabla
        tblAdoptantes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tblAdoptantes.getSelectedRow();
                if (fila != -1) {
                    txtDpi.setText(modeloTabla.getValueAt(fila, 0).toString());
                    txtDpi.setEditable(false); // Bloquea el DPI al editar
                    txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
                    txtTelefono.setText(modeloTabla.getValueAt(fila, 2).toString());
                    txtCorreo.setText(modeloTabla.getValueAt(fila, 3).toString());
                    txtDireccion.setText(modeloTabla.getValueAt(fila, 4).toString());
                }
            }
        });

        // Eventos de botones
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarAdoptante();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarAdoptante();
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

    private void guardarAdoptante() {
        String dpi = txtDpi.getText().trim();
        String nombre = txtNombre.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();
        String direccion = txtDireccion.getText().trim();

        if (dpi.isEmpty() || nombre.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El DPI, Nombre y Teléfono son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

       // Buscamos si el DPI existe en el sistema
        Adoptante existente = adoptanteController.buscarCualquieraPorDpi(dpi);

        if (existente != null && existente.isActivo()) {
            // Si existe Y está activo, editamos
            boolean exitoEdit = adoptanteController.editarAdoptante(dpi, nombre, telefono, direccion, correo);
            if (exitoEdit) {
                JOptionPane.showMessageDialog(this, "Adoptante actualizado correctamente.");
                limpiarCampos();
                cargarDatosTabla();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Si no existe o no está activo, intentamos registrar uno nuevo
            Adoptante nuevo = new Adoptante(dpi, nombre, telefono, direccion, correo, true);
            boolean exito = adoptanteController.agregarAdoptante(nuevo);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Adoptante registrado correctamente.");
                limpiarCampos();
                cargarDatosTabla();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo registrar. Verifique si el DPI ya existe o la capacidad está llena.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarAdoptante() {
        int fila = tblAdoptantes.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un adoptante de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String dpi = (String) modeloTabla.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Desea dar de baja al adoptante con DPI " + dpi + "?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (adoptanteController.eliminarAdoptante(dpi)) {
                JOptionPane.showMessageDialog(this, "Adoptante eliminado con éxito.");
                limpiarCampos();
                cargarDatosTabla();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cargarDatosTabla() {
        modeloTabla.setRowCount(0);
        if (adoptanteController == null) return;

        Adoptante[] activos = adoptanteController.obtenerAdoptantesActivos();
        if (activos != null) {
            for (Adoptante a : activos) {
                if (a != null) {
                    modeloTabla.addRow(new Object[]{
                        a.getDpi(),
                        a.getNombreCompleto(),
                        a.getTelefono(),
                        a.getCorreo(),
                        a.getDireccion()
                    });
                }
            }
        }
    }

    private void limpiarCampos() {
        txtDpi.setText("");
        txtDpi.setEditable(true);
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        tblAdoptantes.clearSelection();
    }
}