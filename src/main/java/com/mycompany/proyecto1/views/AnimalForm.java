
package com.mycompany.proyecto1.views;

import com.mycompany.proyecto1.controllers.AnimalControllers;
import com.mycompany.proyecto1.models.Animal;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.mycompany.proyecto1.controllers.ArchivoControllers;
import java.io.File;

public class AnimalForm extends javax.swing.JFrame {
    
    private AnimalControllers animalController;
    private DefaultTableModel modeloTabla;
    
    //Constructor principal que usara el menu

    public AnimalForm(AnimalControllers animalController) {
        initComponents();
        this.animalController = animalController;
        this.setLocationRelativeTo(null);
        configurarTabla();
        limpiarCampos();//limpia campos y genera el codigo automatico
        cargarDatosTabla();            
    }
    //Constructor sin parametros por defecto para peritir la previsualizacion en el IDE
    public AnimalForm(){
initComponents();
this.animalController = new AnimalControllers(100);//controlador por defecto para evitar null
this.setLocationRelativeTo(null);
limpiarCampos();//limpia los jtxtield por defecto
configurarTabla();
cargarDatosTabla();
}
    //Prepara la tabla para mostrar los registros
    private void configurarTabla(){
        modeloTabla= new DefaultTableModel();
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Especie");
        modeloTabla.addColumn("Edad(estimada)");
        modeloTabla.addColumn("Salud");
        modeloTabla.addColumn("Adopción");
        tblAnimales.setModel(modeloTabla);
        
    }
    //Llena la tabla iterando sobre el arreglo estático
    private void cargarDatosTabla(){
     if (animalController == null) return;

        modeloTabla.setRowCount(0);//limpia filas previas
        Animal[] lista = animalController.obtenerAnimalesActivos();
        
        if (lista != null){
             for(Animal a: lista){
                 if(a != null){
                      Object[] fila = new Object[]{
                         a.getCodigo(),
                         a.getNombre(),
                         a.getEspecie(),
                         a.getEdad(),
                         a.getEstado(),
                         a.getEstadoAdopcion()
                 
                     };
                     modeloTabla.addRow(fila);
                 }
            }   
        }
    }

     private void limpiarCampos(){
          txtNombre.setText("");
          txtEdad.setText("");   
          if (cbxEspecie.getItemCount()>0) cbxEspecie.setSelectedIndex(0);
          if (cbxEstado.getItemCount()>0) cbxEstado.setSelectedIndex(0);
          
          //asigna el codigo correlativo y deshabilita el campo
          generarCodigoAutomatico();
                 
                  
     }
     
     private void generarCodigoAutomatico() {
    txtCodigo.setEditable(false); // Bloquea la edición manual
    
    if (animalController != null && animalController.obtenerAnimalesActivos() != null) {
        // Genera correlativos tipo ANI-001, ANI-002, etc.
        int siguiente = animalController.obtenerAnimalesActivos().length + 1;
        txtCodigo.setText(String.format("ANI-%03d", siguiente));
    } else {
        txtCodigo.setText("ANI-001");
    }
}

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        cbxEspecie = new javax.swing.JComboBox<>();
        cbxEstado = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAnimales = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Código:");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Especie:");

        jLabel4.setText("Edad:");

        jLabel5.setText(" Estado de Salud:");

        txtCodigo.setText("jTextField1");

        txtNombre.setText("jTextField2");

        txtEdad.setText("jTextField3");

        cbxEspecie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Perro", "Gato", "Otro", " " }));

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Saludable", "En Tratamiento", "Crítico", " " }));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        tblAnimales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblAnimales);

        jButton1.setText("Regresar ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setText("REGISTRO DE ANIMALES ");

        jButton2.setText("Generar Reporte");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(147, 147, 147)
                                .addComponent(btnGuardar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(cbxEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 315, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(191, 191, 191))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        //Validacion de seguridad para el controlador
        if (animalController == null){
            JOptionPane.showMessageDialog(this, "Error del sistema: El controlador de animales no esta inicializado.",
            "Error de Sistema", JOptionPane.ERROR_MESSAGE);        
            return;
        }

//Código para el evento del botón "Guardar/Registrar" (doble click al botón en el .form
         try {
             
             String codigo = txtCodigo.getText().trim();
             String nombre = txtNombre.getText().trim();
             String especie = cbxEspecie.getSelectedItem().toString();
             int edad = Integer.parseInt(txtEdad.getText().trim());
             String estado = cbxEstado.getSelectedItem().toString();
             String adopcion = "Disponible";
             
             //Validación de campos vacíos
             if (codigo.isEmpty() || nombre.isEmpty()){
                 JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
                 return;
             }
             Animal nuevoAnimal= new Animal(codigo, nombre, especie, estado, adopcion, true, edad);
             boolean exito = animalController.agregarAnimal(nuevoAnimal);
             
             if (exito){
                 JOptionPane.showMessageDialog(this, "Animal registrado correctamente");
                 cargarDatosTabla(); //Actualiza la tabla
                 limpiarCampos();
             } else {
                 JOptionPane.showMessageDialog(this, "Error: El codigo ya existe o el refugio esta lleno.");
             }  
          } catch (NumberFormatException e){
             JOptionPane.showMessageDialog(this, "La edad debe ser un numero entero valido.");
          }
         
    }
    public static void main(String args[]){
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
                //creamos un controlador de pureba con capacidad para 100 animales
                AnimalControllers controlPrueba = new AnimalControllers(100);
                new AnimalForm(controlPrueba).setVisible(true);
            }
        });
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// 1. Generar el reporte HTML usando el controlador correspondiente
ArchivoControllers.generarReporteHTMLAnimales(animalController.obtenerTodosLosAnimales(), "reporte_animales.html");

// 2. Abrir automáticamente el archivo en el navegador predeterminado
try {
    File archivoHtml = new File("reporte_animales.html");
    if (archivoHtml.exists() && java.awt.Desktop.isDesktopSupported()) {
        java.awt.Desktop.getDesktop().browse(archivoHtml.toURI());
    }
} catch (java.io.IOException ex) {
    javax.swing.JOptionPane.showMessageDialog(this, "El reporte se creó, pero ocurrió un error al abrir el navegador.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
}        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbxEspecie;
    private javax.swing.JComboBox<String> cbxEstado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblAnimales;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}