/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import auxiliar.ModeloTablaConfiguraciones;
import static fichatecnica.FichaTecnica.CONFIG_CORREO;
import java.awt.Frame;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JTable;
import static fichatecnica.FichaTecnica.CONFIG_GENERAL;
import java.util.Properties;
import javax.swing.table.TableColumn;

/**
 *
 * @author coperalta
 */
public class Configuraciones extends javax.swing.JDialog {

    /**
     * Creates new form Configuraciones
     * @param parent
     * @param modal
     */
    public Configuraciones(Frame parent, boolean modal) {
        super(parent, parent.getTitle(), modal);
        initComponents();
        
        cargarPropiedades(tGeneral, CONFIG_GENERAL.getPropiedad());
        cargarPropiedades(tCorreo, CONFIG_CORREO.getPropiedad());
    }
    
    private void cargarPropiedades(JTable tabla, Properties p){        
        Enumeration e= p.propertyNames();
        
        List list = Collections.list(e);
        System.out.println("Total propiedades: "+list.size());
        String c1= CONFIG_GENERAL.getValorColumnaConfiguracion1(),
               c2= CONFIG_GENERAL.getValorColumnaConfiguracion2(),
               c3= CONFIG_GENERAL.getValorColumnaConfiguracion3();
        String colNom[] = {c1, c2, c3};
        tabla.setModel(new ModeloTablaConfiguraciones(list.size(),3, colNom));
        for(int i= 0; i < list.size(); i++){
            tabla.getModel().setValueAt(i, i, 0);
            tabla.getModel().setValueAt(list.get(i), i, 1);
        }
        
        e= p.elements();
        list = Collections.list(e);
        for(int i= 0; i < list.size(); i++){
            tabla.getModel().setValueAt(list.get(i), i, 2);
        }
        
        int ancho= 40;
        TableColumn columna = tabla.getColumn(c1);
        columna.setPreferredWidth(ancho);
        columna.setMinWidth(ancho);
        columna.setMaxWidth(ancho);
        
        tabla.getTableHeader().setReorderingAllowed(false);  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNorte = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlCnfPrincipal = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tGeneral = new javax.swing.JTable();
        pnlCnfCorreo = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tCorreo = new javax.swing.JTable();
        pnlSur = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnDefault = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlNorte.setPreferredSize(new java.awt.Dimension(582, 450));

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(559, 400));

        tGeneral.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Parámetro", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tGeneral);

        javax.swing.GroupLayout pnlCnfPrincipalLayout = new javax.swing.GroupLayout(pnlCnfPrincipal);
        pnlCnfPrincipal.setLayout(pnlCnfPrincipalLayout);
        pnlCnfPrincipalLayout.setHorizontalGroup(
            pnlCnfPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
        );
        pnlCnfPrincipalLayout.setVerticalGroup(
            pnlCnfPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("General", pnlCnfPrincipal);

        tCorreo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Parámetro", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tCorreo);

        javax.swing.GroupLayout pnlCnfCorreoLayout = new javax.swing.GroupLayout(pnlCnfCorreo);
        pnlCnfCorreo.setLayout(pnlCnfCorreoLayout);
        pnlCnfCorreoLayout.setHorizontalGroup(
            pnlCnfCorreoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
        );
        pnlCnfCorreoLayout.setVerticalGroup(
            pnlCnfCorreoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Correo", pnlCnfCorreo);

        pnlNorte.add(jTabbedPane1);

        getContentPane().add(pnlNorte, java.awt.BorderLayout.CENTER);

        btnGuardar.setText("Guardar");
        btnGuardar.setPreferredSize(new java.awt.Dimension(91, 23));
        pnlSur.add(btnGuardar);

        btnCerrar.setText("Cerrar");
        btnCerrar.setPreferredSize(new java.awt.Dimension(91, 23));
        pnlSur.add(btnCerrar);

        btnDefault.setText("Por Defecto");
        pnlSur.add(btnDefault);

        getContentPane().add(pnlSur, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Configuraciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Configuraciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Configuraciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configuraciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Configuraciones dialog = new Configuraciones(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnDefault;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel pnlCnfCorreo;
    private javax.swing.JPanel pnlCnfPrincipal;
    private javax.swing.JPanel pnlNorte;
    private javax.swing.JPanel pnlSur;
    private javax.swing.JTable tCorreo;
    private javax.swing.JTable tGeneral;
    // End of variables declaration//GEN-END:variables
}
