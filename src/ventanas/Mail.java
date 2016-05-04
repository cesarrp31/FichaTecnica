/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import auxiliar.EnviarCorreo;
import auxiliar.LeerPropiedades;
import static fichatecnica.FichaTecnica.NOMBRE_APP;
import static fichatecnica.FichaTecnica.NOMBRE_ARCHIVOS;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author coperalta
 */
public class Mail extends JDialog {
    private EnviarCorreo ec;
    private Properties propiedad;
    /**
     * Creates new form Mail
     * @param parent
     * @param modal
     * @throws java.io.IOException
     */
    public Mail(JFrame parent, boolean modal) throws NullPointerException, IOException {
        super(parent, modal);
        initComponents();
        
        String nomArc= NOMBRE_ARCHIVOS.getProperty("cnfCorreo");
        propiedad = LeerPropiedades.Leer(nomArc);
        
        ec= new EnviarCorreo(propiedad);
        boolean defaultUsuario = Boolean.valueOf(propiedad.getProperty("default.correo.deSistema"));
        if(defaultUsuario){
            tfDe.setText(propiedad.getProperty("default.correo.de"));
        }else{
            tfDe.setText(System.getProperty("user.name"));
        }
        tfDestino.setText(propiedad.getProperty("default.correo.a"));
        this.pack();
    }

    public void datos(String msg){
        this.tfMsg.setText(msg+"\n\n"+NOMBRE_APP);
    }
    
    protected void asunto(String asunto) {
        tfAsunto.setText(propiedad.getProperty("default.correo.a")+asunto);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfDe = new javax.swing.JTextField();
        tfDestino = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfAsunto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        pnlCentral = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tfMsg = new javax.swing.JTextArea();
        pnlSur = new javax.swing.JPanel();
        btEnviar = new javax.swing.JButton();
        btSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        pnlNorte.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("De:");

        jLabel2.setText("A:");

        jLabel3.setText("Asunto:");

        jLabel4.setText("Password");

        javax.swing.GroupLayout pnlNorteLayout = new javax.swing.GroupLayout(pnlNorte);
        pnlNorte.setLayout(pnlNorteLayout);
        pnlNorteLayout.setHorizontalGroup(
            pnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNorteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNorteLayout.createSequentialGroup()
                        .addGroup(pnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(25, 25, 25)
                        .addGroup(pnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfDestino)
                            .addGroup(pnlNorteLayout.createSequentialGroup()
                                .addComponent(tfDe, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pass, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))))
                    .addGroup(pnlNorteLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfAsunto)))
                .addContainerGap())
        );
        pnlNorteLayout.setVerticalGroup(
            pnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNorteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnlNorte, java.awt.BorderLayout.NORTH);

        pnlCentral.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tfMsg.setColumns(20);
        tfMsg.setLineWrap(true);
        tfMsg.setRows(5);
        jScrollPane1.setViewportView(tfMsg);

        javax.swing.GroupLayout pnlCentralLayout = new javax.swing.GroupLayout(pnlCentral);
        pnlCentral.setLayout(pnlCentralLayout);
        pnlCentralLayout.setHorizontalGroup(
            pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
        );
        pnlCentralLayout.setVerticalGroup(
            pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
        );

        getContentPane().add(pnlCentral, java.awt.BorderLayout.CENTER);

        btEnviar.setText("Enviar");
        btEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnviarActionPerformed(evt);
            }
        });
        pnlSur.add(btEnviar);

        btSalir.setText("Cerrar");
        btSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalirActionPerformed(evt);
            }
        });
        pnlSur.add(btSalir);

        getContentPane().add(pnlSur, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalirActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btSalirActionPerformed

    private void btEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnviarActionPerformed
        try {
            char[] password= pass.getPassword();
            if(tfDe.getText().isEmpty()) throw new RuntimeException("No esta cargado el campo \"De\"");
            if(tfDestino.getText().isEmpty()) throw new RuntimeException("No esta cargado el campo \"A\"");
            ec.enviar(tfDe.getText(), new String(password),
                    tfDestino.getText(), tfAsunto.getText(), tfMsg.getText());
            JOptionPane.showMessageDialog(null, "Se envió correctamente el correo!", "Confirmación de envio", JOptionPane.INFORMATION_MESSAGE);
            this.setVisible(false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Error en el envio de correo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btEnviarActionPerformed

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
            java.util.logging.Logger.getLogger(Mail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Mail dialog = new Mail(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (Exception ex) {
                    System.err.println(ex.getLocalizedMessage());
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEnviar;
    private javax.swing.JButton btSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField pass;
    private javax.swing.JPanel pnlCentral;
    private javax.swing.JPanel pnlNorte;
    private javax.swing.JPanel pnlSur;
    private javax.swing.JTextField tfAsunto;
    private javax.swing.JTextField tfDe;
    private javax.swing.JTextField tfDestino;
    private javax.swing.JTextArea tfMsg;
    // End of variables declaration//GEN-END:variables

}
