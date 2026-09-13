package com.mycompany.proyecto1.views;

import com.mycompany.proyecto1.controllers.ArchivoControllers;
import com.mycompany.proyecto1.controllers.RescateControllers;
import com.mycompany.proyecto1.models.Rescate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RescateView extends JFrame {

    private JTextField txtUbicacion;
    private JComboBox<String> cmbNivelUrgencia;
    
    private JButton btnRegistrar;
    private JButton btnAtender;
    private JButton btnLimpiar;
    private JButton btnVolver;
    private JButton btnReporte; // Botón declarado correctamente

    private JTable tblRescates;
    private DefaultTableModel modeloTabla;

    private RescateControllers rescateController;

    public RescateView(RescateControllers rescateController) {
        this.rescateController = rescateController;

        configurarVentana();
        inicializarComponentes();
        cargarDatosTabla();
    }

    private void configurarVentana() {
        setTitle("Módulo de Rescates Urgentes");
        setSize(700, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("REGISTRO DE RESCATES URGENTES", SwingConstants.CENTER);
        lblTitulo.setBounds(20, 15, 640, 25);
        panel.add(lblTitulo);

        // Ubicación / Descripción
        JLabel lblUbicacion = new JLabel("Ubicación:");
        lblUbicacion.setBounds(30, 55, 100, 25);
        panel.add(lblUbicacion);

        txtUbicacion = new JTextField();
        txtUbicacion.setBounds(120, 55, 520, 25);
        panel.add(txtUbicacion);

        // Nivel de Urgencia
        JLabel lblUrgencia = new JLabel("Nivel Urgencia:");
        lblUrgencia.setBounds(30, 95, 100, 25);
        panel.add(lblUrgencia);

        cmbNivelUrgencia = new JComboBox<>(new String[]{"Alta", "Media", "Baja"});
        cmbNivelUrgencia.setBounds(120, 95, 200, 25);
        panel.add(cmbNivelUrgencia);

        // Botones de acción principal
        btnRegistrar = new JButton("Registrar Caso");
        btnRegistrar.setBounds(120, 135, 140, 30);
        panel.add(btnRegistrar);

        btnAtender = new JButton("Marcar Atendido");
        btnAtender.setBounds(275, 135, 150, 30);
        panel.add(btnAtender);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(440, 135, 110, 30);
        panel.add(btnLimpiar);

        // INICIALIZACIÓN CORRECTA DEL BOTÓN DE REPORTE HTML
        btnReporte = new JButton("Ver Reporte HTML");
        btnReporte.setBounds(480, 95, 160, 25);
        panel.add(btnReporte);

        btnReporte.addActionListener(e -> {
            ArchivoControllers.generarReporteHTMLRescates(
                rescateController.obtenerRescates(), 
                "reporte_rescates.html"
            );
            
            try {
                java.io.File archivoHtml = new java.io.File("reporte_rescates.html");
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

        // Tabla de Rescates
        modeloTabla = new DefaultTableModel(new Object[]{"ID Rescate", "Ubicación / Descripción", "Nivel Urgencia", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblRescates = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tblRescates);
        scroll.setBounds(30, 185, 620, 220);
        panel.add(scroll);

        // Botón Volver al Menú Principal
        btnVolver = new JButton("Volver al Menú");
        btnVolver.setBounds(30, 420, 150, 30);
        panel.add(btnVolver);

        // Eventos de botones
        btnRegistrar.addActionListener(e -> registrarRescate());
        btnAtender.addActionListener(e -> atenderRescate());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnVolver.addActionListener(e -> dispose());

        add(panel);
    }

    private void registrarRescate() {
        String ubicacion = txtUbicacion.getText().trim();
        String urgencia = (String) cmbNivelUrgencia.getSelectedItem();

        if (ubicacion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la ubicación o descripción del caso.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String idRescate = "RES-" + (modeloTabla.getRowCount() + 1);

        Rescate nuevo = new Rescate(idRescate, ubicacion, urgencia, "Pendiente");
        boolean exito = rescateController.agregarRescate(nuevo);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Caso " + idRescate + " registrado con éxito.");
            limpiarCampos();
            cargarDatosTabla();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo registrar el caso. Verifique el almacenamiento.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atenderRescate() {
        int fila = tblRescates.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un caso de la tabla para marcarlo como atendido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String idRescate = (String) modeloTabla.getValueAt(fila, 0);

        if (rescateController.atenderRescate(idRescate)) {
            JOptionPane.showMessageDialog(this, "El caso " + idRescate + " ha sido marcado como Atendido.");
            cargarDatosTabla();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el estado del caso.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatosTabla() {
        modeloTabla.setRowCount(0);
        if (rescateController == null) return;

        Rescate[] lista = rescateController.obtenerRescates();
        if (lista != null) {
            for (Rescate r : lista) {
                if (r != null) {
                    modeloTabla.addRow(new Object[]{
                        r.getIdRescate(),
                        r.getDescripcionUbicacion(),
                        r.getNivelUrgencia(),
                        r.getEstado()
                    });
                }
            }
        }
    }

    private void limpiarCampos() {
        txtUbicacion.setText("");
        cmbNivelUrgencia.setSelectedIndex(0);
    }
}