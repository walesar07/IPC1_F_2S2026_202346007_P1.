package com.mycompany.proyecto1.views;

import com.mycompany.proyecto1.controllers.AnimalControllers;
import com.mycompany.proyecto1.controllers.UbicacionControllers;
import com.mycompany.proyecto1.models.Animal;
import com.mycompany.proyecto1.models.EspacioRefugio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UbicacionView extends JFrame {

    private JComboBox<String> cmbAnimales;
    private JSpinner spnFila;
    private JSpinner spnColumna;

    private JButton btnAsignar;
    private JButton btnLiberar;
    private JButton btnVolver;

    private JTable tblMatriz;
    private DefaultTableModel modeloTabla;

    private UbicacionControllers ubicacionController;
    private AnimalControllers animalController;

    public UbicacionView(UbicacionControllers ubicacionController, AnimalControllers animalController) {
        this.ubicacionController = ubicacionController;
        this.animalController = animalController;

        configurarVentana();
        inicializarComponentes();
        cargarAnimales();
        actualizarMatrizVisual();
    }

    private void configurarVentana() {
        setTitle("Mapa de Ubicaciones del Refugio (Matriz de Jaulas)");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("PANEL DE CONTROL DE ESPACIOS Y JAULAS", SwingConstants.CENTER);
        lblTitulo.setBounds(20, 15, 690, 25);
        panel.add(lblTitulo);

        // Selección de Animal
        JLabel lblAnimal = new JLabel("Animal:");
        lblAnimal.setBounds(30, 55, 80, 25);
        panel.add(lblAnimal);

        cmbAnimales = new JComboBox<>();
        cmbAnimales.setBounds(100, 55, 220, 25);
        panel.add(cmbAnimales);

        // Selección de Fila (Área)
        JLabel lblFila = new JLabel("Área (Fila):");
        lblFila.setBounds(340, 55, 80, 25);
        panel.add(lblFila);

        int maxFilas = ubicacionController != null ? ubicacionController.getFilas() : 5;
        spnFila = new JSpinner(new SpinnerNumberModel(0, 0, Math.max(0, maxFilas - 1), 1));
        spnFila.setBounds(420, 55, 60, 25);
        panel.add(spnFila);

        // Selección de Columna (Jaula)
        JLabel lblCol = new JLabel("Jaula (Col):");
        lblCol.setBounds(500, 55, 80, 25);
        panel.add(lblCol);

        int maxCols = ubicacionController != null ? ubicacionController.getColumnas() : 5;
        spnColumna = new JSpinner(new SpinnerNumberModel(0, 0, Math.max(0, maxCols - 1), 1));
        spnColumna.setBounds(580, 55, 60, 25);
        panel.add(spnColumna);

        // Botones
        btnAsignar = new JButton("Asignar Espacio");
        btnAsignar.setBounds(200, 95, 150, 30);
        panel.add(btnAsignar);

        btnLiberar = new JButton("Liberar Espacio");
        btnLiberar.setBounds(370, 95, 150, 30);
        panel.add(btnLiberar);
// Crear el botón de regreso
btnVolver = new JButton("Volver al Menú");

// Posición y tamaño: (X, Y, Ancho, Alto)
// Ajusta X e Y según la esquina inferior donde tengas espacio libre en ese panel
btnVolver.setBounds(30, 410, 150, 30); 

// Acción para cerrar únicamente la ventana actual
btnVolver.addActionListener(e -> dispose());

// Agregar al panel de la interfaz
panel.add(btnVolver);
        // Tabla Representativa de la Matriz
        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblMatriz = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tblMatriz);
        scroll.setBounds(30, 140, 670, 290);
        panel.add(scroll);

        // Listeners
        btnAsignar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                asignarEspacio();
            }
        });

        btnLiberar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                liberarEspacio();
            }
        });

        add(panel);
    }

    private void cargarAnimales() {
        cmbAnimales.removeAllItems();
        if (animalController != null && animalController.obtenerAnimalesActivos() != null) {
            for (Animal a : animalController.obtenerAnimalesActivos()) {
                if (a != null) {
                    cmbAnimales.addItem(a.getCodigo() + " - " + a.getNombre());
                }
            }
        }
    }

    private void asignarEspacio() {
        if (cmbAnimales.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "No hay animales registrados para asignar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String selAnimal = (String) cmbAnimales.getSelectedItem();
        String codigoAnimal = selAnimal.split(" - ")[0];

        int f = (int) spnFila.getValue();
        int c = (int) spnColumna.getValue();

        if (ubicacionController.asignarUbicacion(f, c, codigoAnimal)) {
            JOptionPane.showMessageDialog(this, "Animal " + codigoAnimal + " asignado a Área [" + f + "], Jaula [" + c + "]");
            actualizarMatrizVisual();
        } else {
            JOptionPane.showMessageDialog(this, "El espacio en Área [" + f + "], Jaula [" + c + "] ya está ocupado o no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void liberarEspacio() {
        int f = (int) spnFila.getValue();
        int c = (int) spnColumna.getValue();

        if (ubicacionController.liberarUbicacion(f, c)) {
            JOptionPane.showMessageDialog(this, "Espacio en Área [" + f + "], Jaula [" + c + "] liberado.");
            actualizarMatrizVisual();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo liberar la posición seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarMatrizVisual() {
        if (ubicacionController == null) return;

        EspacioRefugio[][] matriz = ubicacionController.getMatrizRefugio();
        int filas = ubicacionController.getFilas();
        int cols = ubicacionController.getColumnas();

        // Configurar Columnas
        String[] cabecera = new String[cols + 1];
        cabecera[0] = "Área / Jaula";
        for (int j = 0; j < cols; j++) {
            cabecera[j + 1] = "Jaula " + j;
        }

        modeloTabla.setDataVector(null, cabecera);

        // Llenar Filas
        for (int i = 0; i < filas; i++) {
            Object[] filaDatos = new Object[cols + 1];
            filaDatos[0] = "Área " + i;

            for (int j = 0; j < cols; j++) {
                EspacioRefugio esp = matriz[i][j];
                if (esp != null && esp.isOcupado()) {
                    filaDatos[j + 1] = "[ " + esp.getCodigoAnimal() + " ]";
                } else {
                    filaDatos[j + 1] = "Disponible";
                }
            }
            modeloTabla.addRow(filaDatos);
        }
    }
}
