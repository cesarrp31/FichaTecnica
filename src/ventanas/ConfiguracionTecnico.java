/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import static fichatecnica.FichaTecnica.CONFIG_TECNICO;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author coperalta
 */
class ConfiguracionTecnico extends javax.swing.JDialog {
    //private FichaTecnicaImpresion padre;
    /**
     * Creates new form ConfiguracionTecnico
     */
    public ConfiguracionTecnico(JFrame padre, boolean modal) {
        super(padre, padre.getTitle(), modal);
        initComponents();
        
        tfTecnico.setText(CONFIG_TECNICO.getNombreTecnico());
    }
    
    private void cerrar(){
        this.dispose();
    }
    
    private void aplicar(){
        try {
            CONFIG_TECNICO.setNombreTecnico(tfTecnico.getText());
            ((FichaTecnicaImpresion)this.getParent()).actualizarNombreTecnico(tfTecnico.getText());
        } catch (IOException ex) {
            System.err.println("No se puede guardar el valor en el archivo. "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    private void guardar(){
        aplicar();
        cerrar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        pnCentral = new javax.swing.JPanel();
        tfTecnico = new javax.swing.JTextField();
        pnSur = new javax.swing.JPanel();
        bnGuardar = new javax.swing.JButton();
        bnAplicar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setLayout(new java.awt.BorderLayout());

        pnCentral.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre del Técnico"));

        tfTecnico.setMaximumSize(new java.awt.Dimension(400, 24));
        tfTecnico.setMinimumSize(new java.awt.Dimension(400, 24));
        tfTecnico.setPreferredSize(new java.awt.Dimension(400, 24));
        pnCentral.add(tfTecnico);

        jPanel2.add(pnCentral, java.awt.BorderLayout.CENTER);

        bnGuardar.setText("Guardar");
        bnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnGuardarActionPerformed(evt);
            }
        });
        pnSur.add(bnGuardar);

        bnAplicar.setText("Aplicar");
        bnAplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnAplicarActionPerformed(evt);
            }
        });
        pnSur.add(bnAplicar);

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        pnSur.add(btnCerrar);

        jPanel2.add(pnSur, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.cerrar();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void bnAplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnAplicarActionPerformed
        this.aplicar();
    }//GEN-LAST:event_bnAplicarActionPerformed

    private void bnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnGuardarActionPerformed
        this.guardar();
    }//GEN-LAST:event_bnGuardarActionPerformed

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
            java.util.logging.Logger.getLogger(ConfiguracionTecnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfiguracionTecnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfiguracionTecnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfiguracionTecnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConfiguracionTecnico dialog = new ConfiguracionTecnico(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton bnAplicar;
    private javax.swing.JButton bnGuardar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel pnCentral;
    private javax.swing.JPanel pnSur;
    private javax.swing.JTextField tfTecnico;
    // End of variables declaration//GEN-END:variables
}