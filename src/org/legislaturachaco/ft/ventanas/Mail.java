/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.ft.ventanas;

import org.legislaturachaco.com.gral.EnviarCorreo;
import org.legislaturachaco.com.gral.GestorArchivo;
import org.legislaturachaco.ft.datos.DatosFichaTecnica;
import static org.legislaturachaco.ft.FichaTecnica.CONFIG_CORREO;
import static org.legislaturachaco.ft.FichaTecnica.CONFIG_TECNICO;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static org.legislaturachaco.ft.FichaTecnica.CONFIG_GENERAL;
import org.legislaturachaco.com.gral.GestorEntornoEjecucion;

/**
 *
 * @author coperalta
 */
public class Mail extends JDialog {
    private final EnviarCorreo ec;
    private final String crpImg = CONFIG_GENERAL.getCarpetaImagenes() + GestorArchivo.SEPARADOR,
            path= CONFIG_GENERAL.getImagenCorreoPie();
    
    private String msgMail, msgPantalla;
    
    protected Mail(JFrame parent, boolean modal, EnviarCorreo ec) throws NullPointerException, IOException{
        super(parent, parent.getTitle(), modal);
        initComponents();
        
        this.ec= ec;
        int defaultUsuario = Integer.valueOf(CONFIG_CORREO.getDefaultCorreoUser());
        switch (defaultUsuario){
            case 0: tfDe.setText(CONFIG_CORREO.getDefaultCorreoEmisor()); break;
            case 1: tfDe.setText(GestorEntornoEjecucion.getNombreUsuario()); break;
            default: tfDe.setText(CONFIG_TECNICO.getUsuarioTecnico());
        }
        tfDestino.setText(CONFIG_CORREO.getDefaultCorreoDestinatario());
        
        //Este panel no se usará hasta que se use el nuevo servidor exchange 2019
        this.pnlOeste.setVisible(false);
    }
    
    /**
     * Creates new form Mail
     * @param parent
     * @param modal
     * @throws java.io.IOException
     */
    public Mail(JFrame parent, boolean modal) throws NullPointerException, IOException {
        this(parent, modal, new EnviarCorreo());
    }
    
    public Mail(JFrame parent, boolean modal, Properties propiedades) throws NullPointerException, IOException {
        this(parent, modal, new EnviarCorreo(propiedades));
    }
    
    public void cargarDatos(DatosFichaTecnica dft) {
        String tarea, componente, nota, usuario, clave, aclaracion;
        if(dft.getTarea().isEmpty()){
            tarea= "";
        }else{
            tarea= "<b>Tareas Realizadas: </b>" + dft.getTarea() +"<br>";
        }
        if(dft.getComponentes().isEmpty()){
            componente= "";
        }else{
            componente= "<b>Componentes Utilizados: </b>" + dft.getComponentes() +"<br>";
        }        
        if(dft.getNroNota().isEmpty()){
            nota= "";
        }else{
            nota= agregarLineaB("A/S", dft.getNroNota());
        }
        if(dft.getUsuario().isEmpty()){
            usuario="";
            clave= "";
            aclaracion="";
        }else{
            usuario= agregarLineaB("Usuario PC", dft.getUsuario());
            clave= agregarLineaB("Contraseña", dft.getClave());
            aclaracion= "<b>&#09&#09Es recomendable cambiar la contraseña!</b><br>";
        }
        
        String msgComun= "<b>Dependencia: </b>" + dft.getDependencia() +"<br>"+
                     "<b>Fecha: </b>" + dft.getFecha() +"<br>"+
                     agregarLineaB("Solicitado por", dft.getIngresoSeleccionado())+
                     nota +
                     "<b>Patrimonio/s: </b>" + dft.getPatrimonio() +"<br>"+
                     "<b>Ponderación: </b>" + dft.getPonderacion() +"<br>"+
                     tarea + componente + usuario + clave + aclaracion +
                     "<b>Técnico/s: </b>" + dft.getTecnico() +"<br>"+"<br>"+
                     "<h3><b>Estado del Servicio Técnico: </b>" + dft.getEstado() +"</h3><br>" +
                     crearTabla(dft)+"<br>";
        msgPantalla="<head><base href=\"file:"+crpImg+"\"></head>"+msgComun+"<img src=\""+path+"\"></img>";
        msgMail=msgComun+"<br><br><img src=\"cid:image\"></img>";
        
        this.epMsg.setText(msgPantalla);        
        this.btEnviar.requestFocus();
    }
    
    private String crearTabla(DatosFichaTecnica dft){
        boolean crearTabla= Boolean.valueOf(CONFIG_GENERAL.getCorreoEnviarTabla());
        if(!crearTabla) return "";
        
        StringBuilder sb= new StringBuilder();
        String aux= dft.getNroNota().isEmpty()?"":"A/S: "+dft.getNroNota()+".";
        
        sb.append("<table>");
        sb.append("<tr>");
        
        sb.append(agregarColumnaTabla("0"));
        sb.append(agregarColumnaTabla(dft.getFecha()));
        sb.append(agregarColumnaTabla(dft.getDependencia()));
        sb.append(agregarColumnaTabla(dft.getPatrimonio()));
        sb.append(agregarColumnaTabla(dft.getTarea()+"\n"+dft.getComponentes()+"\nPonderación: "+dft.getPonderacion()));
        sb.append(agregarColumnaTabla(dft.getTecnico()));
        sb.append(agregarColumnaTabla(aux+"\nSolicitado por: "+dft.getIngresoSeleccionado()+".\nEstado: "+dft.getEstado()));
        
        sb.append("</tr>");
        sb.append("</table>");
        return sb.toString();
    }
    private String agregarColumnaTabla(String dato){
        return "<td>"+dato+"</td>";
    }
        
    private String agregarLineaB(String estatico, String dinamico){
        return "<b>"+estatico+": </b>" + dinamico +"<br>";
    }
    
    protected void asunto(String asunto) {
        tfAsunto.setText(CONFIG_CORREO.getDefaultCorreoAsunto()+asunto);
    }
    
    protected void asunto(String asunto, String nroASimple) {
        String aux= asunto;
        
        if(!nroASimple.isEmpty()){
            aux= aux + " - A/S:" + nroASimple;
        }
        
        tfAsunto.setText(CONFIG_CORREO.getDefaultCorreoAsunto()+aux);
    }
    
    protected void actualizarUsuario(){
        tfDe.setText(CONFIG_TECNICO.getUsuarioTecnico());
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
        jScrollPane2 = new javax.swing.JScrollPane();
        epMsg = new javax.swing.JEditorPane();
        pnlSur = new javax.swing.JPanel();
        btEnviar = new javax.swing.JButton();
        btSalir = new javax.swing.JButton();
        pnlOeste = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        epTarea = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));

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
                                .addComponent(pass, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))))
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

        epMsg.setContentType("text/html"); // NOI18N
        jScrollPane2.setViewportView(epMsg);

        javax.swing.GroupLayout pnlCentralLayout = new javax.swing.GroupLayout(pnlCentral);
        pnlCentral.setLayout(pnlCentralLayout);
        pnlCentralLayout.setHorizontalGroup(
            pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
        );
        pnlCentralLayout.setVerticalGroup(
            pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
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

        epTarea.setEditable(false);
        epTarea.setContentType("text/html"); // NOI18N
        jScrollPane3.setViewportView(epTarea);

        javax.swing.GroupLayout pnlOesteLayout = new javax.swing.GroupLayout(pnlOeste);
        pnlOeste.setLayout(pnlOesteLayout);
        pnlOesteLayout.setHorizontalGroup(
            pnlOesteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOesteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlOesteLayout.setVerticalGroup(
            pnlOesteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
        );

        getContentPane().add(pnlOeste, java.awt.BorderLayout.WEST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btSalirActionPerformed

    private void btEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnviarActionPerformed
        String tipoMensaje= "text/html; charset="+
               CONFIG_GENERAL.getConfiguracionCodificacion().toLowerCase();
        try {
            char[] password= pass.getPassword();
            if(tfDe.getText().isEmpty()) throw new RuntimeException("No esta cargado el campo \"De\"");
            if(tfDestino.getText().isEmpty()) throw new RuntimeException("No esta cargado el campo \"A\"");
            ec.enviarCorreoImagen(tfDe.getText(), new String(password),
                    tfDestino.getText(), tfAsunto.getText(), msgMail, crpImg+path, tipoMensaje);
            JOptionPane.showMessageDialog(null, "Se envió correctamente el correo!", "Confirmación de envio", JOptionPane.INFORMATION_MESSAGE);
            this.setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
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
    private javax.swing.JEditorPane epMsg;
    private javax.swing.JEditorPane epTarea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPasswordField pass;
    private javax.swing.JPanel pnlCentral;
    private javax.swing.JPanel pnlNorte;
    private javax.swing.JPanel pnlOeste;
    private javax.swing.JPanel pnlSur;
    private javax.swing.JTextField tfAsunto;
    private javax.swing.JTextField tfDe;
    private javax.swing.JTextField tfDestino;
    // End of variables declaration//GEN-END:variables

}