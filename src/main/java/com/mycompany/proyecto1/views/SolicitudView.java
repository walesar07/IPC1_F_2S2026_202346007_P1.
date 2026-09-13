package com.mycompany.proyecto1.views;

import com.mycompany.proyecto1.controllers.AdoptanteControllers;
import com.mycompany.proyecto1.controllers.AnimalControllers;
import com.mycompany.proyecto1.controllers.ArchivoControllers;
import com.mycompany.proyecto1.controllers.SolicitudControllers;
import com.mycompany.proyecto1.models.Adoptante;
import com.mycompany.proyecto1.models.Animal;
import com.mycompany.proyecto1.models.Solicitud;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolicitudView extends JFrame {

    private JComboBox<String> cmbAnimales;
    private JComboBox<String> cmbAdoptantes;
    private JComboBox<String> cmbEstado;

    private JButton btnCrearSolicitud;
    private JButton btnCambiarEstado;
    private JButton btnLimpiar;
    private JButton btnVolver;
    private JButton btnReporte; // Botón declarado correctamente

    private JTable tblSolicitudes;
    private DefaultTableModel modeloTabla;

    private SolicitudControllers solicitudController;
    private AnimalControllers animalController;
    private AdoptanteControllers adoptanteController;

    public SolicitudView(SolicitudControllers solicitudController, AnimalControllers animalController, AdoptanteControllers adoptanteController) {
        this.solicitudController = solicitudController;
        this.animalController = animalController;
        this.adoptanteController = adoptanteController;

        configurarVentana();
        inicializarComponentes();
        cargarCombos();
        cargarDatosTabla();
    }

    private void configurarVentana() {
        setTitle("Gestión de Solicitudes de Adopción");
        setSize(700, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("SOLICITUDES DE ADOPCIÓN", SwingConstants.CENTER);
        lblTitulo.setBounds(20, 15, 640, 25);
        panel.add(lblTitulo);

        // Seleccionar Animal
        JLabel lblAnimal = new JLabel("Animal:");
        lblAnimal.setBounds(30, 55, 100, 25);
        panel.add(lblAnimal);

        cmbAnimales = new JComboBox<>();
        cmbAnimales.setBounds(120, 55, 200, 25);
        panel.add(cmbAnimales);

        // Seleccionar Adoptante
        JLabel lblAdoptante = new JLabel("Adoptante:");
        lblAdoptante.setBounds(340, 55, 100, 25);
        panel.add(lblAdoptante);

        cmbAdoptantes = new JComboBox<>();
        cmbAdoptantes.setBounds(430, 55, 220, 25);
        panel.add(cmbAdoptantes);

        // Estado de Solicitud
        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(30, 95, 100, 25);
        panel.add(lblEstado);

        cmbEstado = new JComboBox<>(new String[]{"Pendiente", "Aprobada", "Rechazada"});
        cmbEstado.setBounds(120, 95, 200, 25);
        panel.add(cmbEstado);

        // Botones de Acción
        btnCrearSolicitud = new JButton("Crear Solicitud");
        btnCrearSolicitud.setBounds(120, 140, 130, 30);
        panel.add(btnCrearSolicitud);

        btnCambiarEstado = new JButton("Actualizar Estado");
        btnCambiarEstado.setBounds(265, 140, 150, 30);
        panel.add(btnCambiarEstado);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(430, 140, 110, 30);
        panel.add(btnLimpiar);

        // INICIALIZACIÓN CORRECTA DEL BOTÓN DE REPORTE HTML
        btnReporte = new JButton("Ver Reporte HTML");
        btnReporte.setBounds(500, 95, 150, 25);
        panel.add(btnReporte);

        btnReporte.addActionListener(e -> {
            ArchivoControllers.generarReporteHTMLSolicitudes(
                solicitudController.obtenerSolicitud(), 
                "reporte_solicitudes.html"
            );
            
            try {
                java.io.File archivoHtml = new java.io.File("reporte_solicitudes.html");
                if (archivoHtml.exists() && java.awt.Desktop.isDesktopSupported()) {
                    java.awt.Desktop.getDesktop().browse(archivoHtml.toURI());
                }
            } catch (java.io.IOException ex) {
                JOptionPane.showMessageDialog(this, 
                    "El reporte se creó, pero ocurrió un error al abrir el navegador.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        // Tabla de Solicitudes
        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Código Animal", "DPI Adoptante", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblSolicitudes = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tblSolicitudes);
        scroll.setBounds(30, 190, 620, 210);
        panel.add(scroll);

        // Botón Volver al Menú Principal
        btnVolver = new JButton("Volver al Menú");
        btnVolver.setBounds(30, 420, 150, 30);
        panel.add(btnVolver);

        // Listeners
        btnCrearSolicitud.addActionListener(e -> crearSolicitud());
        btnCambiarEstado.addActionListener(e -> actualizarEstado());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnVolver.addActionListener(e -> dispose());

        add(panel);
    }

    private void cargarCombos() {
        cmbAnimales.removeAllItems();
        cmbAdoptantes.removeAllItems();

        if (animalController != null && animalController.obtenerAnimalesActivos() != null) {
            for (Animal a : animalController.obtenerAnimalesActivos()) {
                if (a != null) {
                    cmbAnimales.addItem(a.getCodigo() + " - " + a.getNombre());
                }
            }
        }

        if (adoptanteController != null && adoptanteController.obtenerAdoptantesActivos() != null) {
            for (Adoptante ad : adoptanteController.obtenerAdoptantesActivos()) {
                if (ad != null) {
                    cmbAdoptantes.addItem(ad.getDpi() + " - " + ad.getNombreCompleto());
                }
            }
        }
    }

    private void crearSolicitud() {
        if (cmbAnimales.getItemCount() == 0 || cmbAdoptantes.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "Debe registrar animales y adoptantes primero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String selAnimal = (String) cmbAnimales.getSelectedItem();
        String selAdoptante = (String) cmbAdoptantes.getSelectedItem();

        String codigoAnimal = selAnimal.split(" - ")[0];
        String dpiAdoptante = selAdoptante.split(" - ")[0];
        String estado = (String) cmbEstado.getSelectedItem();

        String idSolicitud = "SOL-" + (solicitudController.getContador() + 1);

        Solicitud nueva = new Solicitud(idSolicitud, dpiAdoptante, codigoAnimal, estado);
        boolean exito = solicitudController.agregarSolicitud(nueva);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Solicitud " + idSolicitud + " creada correctamente.");
            cargarDatosTabla();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo registrar la solicitud.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarEstado() {
        int fila = tblSolicitudes.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una solicitud de la tabla para modificar su estado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String idSolicitud = (String) modeloTabla.getValueAt(fila, 0);
        String nuevoEstado = (String) cmbEstado.getSelectedItem();

        if (solicitudController.cambiarEstado(idSolicitud, nuevoEstado)) {
            JOptionPane.showMessageDialog(this, "Estado actualizado a: " + nuevoEstado);
            cargarDatosTabla();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el estado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatosTabla() {
        modeloTabla.setRowCount(0);
        if (solicitudController == null) return;

        Solicitud[] lista = solicitudController.obtenerSolicitud();
        if (lista != null) {
            for (Solicitud s : lista) {
                if (s != null) {
                    modeloTabla.addRow(new Object[]{
                        s.getIdSolicitud(),
                        s.getCodigoAnimal(),
                        s.getDpiAdoptante(),
                        s.getEstado()
                    });
                }
            }
        }
    }

    private void limpiarCampos() {
        if (cmbAnimales.getItemCount() > 0) cmbAnimales.setSelectedIndex(0);
        if (cmbAdoptantes.getItemCount() > 0) cmbAdoptantes.setSelectedIndex(0);
        cmbEstado.setSelectedIndex(0);
    }
}