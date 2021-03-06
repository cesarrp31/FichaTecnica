/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.ft.ventanas;

import org.legislaturachaco.com.gral.GestorArchivo;
import static org.legislaturachaco.ft.FichaTecnica.CONFIG_GENERAL;
import java.io.FileNotFoundException;

/**
 *
 * @author coperalta
 */
public class PnlAcercaDe extends javax.swing.JPanel {

    /**
     * Creates new form PnlAcercaDe
     */
    public PnlAcercaDe() {
        initComponents();
        
        String logo = CONFIG_GENERAL.getCarpetaImagenes() + GestorArchivo.SEPARADOR + CONFIG_GENERAL.getImagenCorreoPie();
        try {
            icono.setIcon(GestorArchivo.crearImageIcon(logo, ""));
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
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
        icono = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(640, 480));
        setMinimumSize(new java.awt.Dimension(640, 480));
        setPreferredSize(new java.awt.Dimension(640, 480));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel2.add(icono);

        add(jPanel2);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Diseñado y Desarrollado por:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24))); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("<html>\n<body>\n<h2>Téc. Administración de Empresas Gallo, Mario<br>\nTéc. Superior en Programación Silva, Jonatan<br>\nAnalista de Sistemas Peralta, Cesar</h2>\n</body>\n</html>");
        jPanel1.add(jLabel2);

        add(jPanel1);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información de Contacto:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24))); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("<html> <body> <h2>Dirección de Sistemas y Comunicaciones<br>Dir: José María Paz 170 - 1° Piso<br> Correo: soporte@legislaturachaco.gov.ar<br> Tel: (0362) 4428149</h2> </body> </html>");
        jPanel3.add(jLabel3);

        add(jPanel3);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icono;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
