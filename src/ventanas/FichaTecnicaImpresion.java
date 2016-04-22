/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import fichatecnica.FichaTecnica;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author jsilva
 */
public class FichaTecnicaImpresion extends javax.swing.JFrame {

    /**
     * Creates new form FichaTecnicaImpresion
     */
    public FichaTecnicaImpresion() {
        initComponents();
        
        inicializar();
    }
    
    private void inicializar() {
        try {
            System.out.println(System.getProperty("java.class.path"));
            String logo= "logo_camara.png", 
                    valores="tareas.csv", val="componentes.csv";
            
            ImageIcon ii= createImageIcon(logo, "");                        
            llogo.setIcon(ii);
            superlogosolo.add(llogo);

            File archTareas = new File(this.getClass().getClassLoader().getResource(valores).getFile());
            File archComponentes = new File(this.getClass().getClassLoader().getResource(val).getFile());
            
            //Creating Scanner instnace to read File in Java
            Scanner scnr = new Scanner(archTareas);
            
            //Reading each line of file using Scanner class
            while(scnr.hasNextLine()){
                String line = scnr.nextLine();
                cbtareas.addItem(line);
            }
            
            scnr = new Scanner(archComponentes);
            while(scnr.hasNextLine()){
                String line = scnr.nextLine();
                cbComponentes.addItem(line);
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
        
        this.tatareas.setText("");
        this.tacomponentes.setText("");
        Date actual=new Date();
        tffecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(actual));
    }
    
    protected ImageIcon createImageIcon(String path,
                                           String description) {
        File img = new File(this.getClass().getClassLoader().getResource(path).getFile());
        //URL imgURL = getClass().getResource(path);
        if (img.getAbsolutePath() != null) {
            return new ImageIcon(img.getAbsolutePath(), description);
        } else {
            System.err.println("No se encuentra la imagen: " + path);
            return null;
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

        centro = new javax.swing.JPanel();
        superiortareas = new javax.swing.JPanel();
        ltareas = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tatareas = new javax.swing.JTextArea();
        inferiorcomponentes = new javax.swing.JPanel();
        lcomponentes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tacomponentes = new javax.swing.JTextArea();
        superior = new javax.swing.JPanel();
        superiologo = new javax.swing.JPanel();
        superlogosolo = new javax.swing.JPanel();
        llogo = new javax.swing.JLabel();
        superdatos = new javax.swing.JPanel();
        lnombre = new javax.swing.JLabel();
        ltel = new javax.swing.JLabel();
        ltel2 = new javax.swing.JLabel();
        ldir = new javax.swing.JLabel();
        infdatos = new javax.swing.JPanel();
        ltitulo = new javax.swing.JLabel();
        ldependencia = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfdependencia = new javax.swing.JTextField();
        tffecha = new javax.swing.JTextField();
        lpatrimonio = new javax.swing.JLabel();
        tfpatrimonio = new javax.swing.JTextField();
        inferior = new javax.swing.JPanel();
        icentro = new javax.swing.JPanel();
        icfirma = new javax.swing.JLabel();
        tffirma = new javax.swing.JTextField();
        iizq = new javax.swing.JPanel();
        iitecnico = new javax.swing.JLabel();
        tftecnico = new javax.swing.JTextField();
        ider = new javax.swing.JPanel();
        idaclaracion = new javax.swing.JLabel();
        tfaclaracion = new javax.swing.JTextField();
        derecha = new javax.swing.JPanel();
        cbtareas = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        izquierda = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbComponentes = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        centro.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        centro.setLayout(new java.awt.GridLayout(2, 1));

        ltareas.setText("Se Realizo:");

        tatareas.setColumns(20);
        tatareas.setRows(5);
        jScrollPane2.setViewportView(tatareas);

        javax.swing.GroupLayout superiortareasLayout = new javax.swing.GroupLayout(superiortareas);
        superiortareas.setLayout(superiortareasLayout);
        superiortareasLayout.setHorizontalGroup(
            superiortareasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(superiortareasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ltareas)
                .addContainerGap(992, Short.MAX_VALUE))
            .addGroup(superiortareasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(superiortareasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1035, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        superiortareasLayout.setVerticalGroup(
            superiortareasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(superiortareasLayout.createSequentialGroup()
                .addComponent(ltareas)
                .addContainerGap(104, Short.MAX_VALUE))
            .addGroup(superiortareasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(superiortareasLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addGap(21, 21, 21)))
        );

        centro.add(superiortareas);

        lcomponentes.setText("Componentes utilizados:");

        tacomponentes.setColumns(20);
        tacomponentes.setRows(5);
        jScrollPane1.setViewportView(tacomponentes);

        javax.swing.GroupLayout inferiorcomponentesLayout = new javax.swing.GroupLayout(inferiorcomponentes);
        inferiorcomponentes.setLayout(inferiorcomponentesLayout);
        inferiorcomponentesLayout.setHorizontalGroup(
            inferiorcomponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inferiorcomponentesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inferiorcomponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inferiorcomponentesLayout.createSequentialGroup()
                        .addComponent(lcomponentes)
                        .addGap(0, 918, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        inferiorcomponentesLayout.setVerticalGroup(
            inferiorcomponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inferiorcomponentesLayout.createSequentialGroup()
                .addComponent(lcomponentes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        centro.add(inferiorcomponentes);

        getContentPane().add(centro, java.awt.BorderLayout.CENTER);

        superior.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        superior.setLayout(new java.awt.GridLayout(2, 1));

        javax.swing.GroupLayout superlogosoloLayout = new javax.swing.GroupLayout(superlogosolo);
        superlogosolo.setLayout(superlogosoloLayout);
        superlogosoloLayout.setHorizontalGroup(
            superlogosoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
            .addGroup(superlogosoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(superlogosoloLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(llogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        superlogosoloLayout.setVerticalGroup(
            superlogosoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 105, Short.MAX_VALUE)
            .addGroup(superlogosoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(superlogosoloLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addComponent(llogo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        superiologo.add(superlogosolo);

        lnombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lnombre.setText("Direccion de Comunicaciones");

        ltel.setText("Tel: (0362) 4428149");

        ltel2.setText("Centrex: 28149 ");

        ldir.setText("José María Paz 170 -1° Piso  ");

        javax.swing.GroupLayout superdatosLayout = new javax.swing.GroupLayout(superdatos);
        superdatos.setLayout(superdatosLayout);
        superdatosLayout.setHorizontalGroup(
            superdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 211, Short.MAX_VALUE)
            .addGroup(superdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(superdatosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(superdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ldir, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ltel, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ltel2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        superdatosLayout.setVerticalGroup(
            superdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 106, Short.MAX_VALUE)
            .addGroup(superdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(superdatosLayout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(lnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(ldir)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(ltel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(ltel2)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        superiologo.add(superdatos);

        superior.add(superiologo);

        ltitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ltitulo.setText("SOPORTE TECNICO");

        ldependencia.setText("Dependencia:");

        jLabel3.setText("Fecha:");

        tfdependencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfdependenciaActionPerformed(evt);
            }
        });

        tffecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tffechaActionPerformed(evt);
            }
        });

        lpatrimonio.setText("Nº Patrimonio");

        tfpatrimonio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfpatrimonioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout infdatosLayout = new javax.swing.GroupLayout(infdatos);
        infdatos.setLayout(infdatosLayout);
        infdatosLayout.setHorizontalGroup(
            infdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infdatosLayout.createSequentialGroup()
                .addGroup(infdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infdatosLayout.createSequentialGroup()
                        .addGroup(infdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(infdatosLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(ldependencia))
                            .addGroup(infdatosLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lpatrimonio)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(infdatosLayout.createSequentialGroup()
                                .addComponent(tfdependencia, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tffecha, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE))
                            .addComponent(tfpatrimonio)))
                    .addGroup(infdatosLayout.createSequentialGroup()
                        .addGap(417, 417, 417)
                        .addComponent(ltitulo)))
                .addContainerGap())
        );
        infdatosLayout.setVerticalGroup(
            infdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infdatosLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(ltitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ldependencia)
                    .addComponent(tfdependencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(tffecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(infdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lpatrimonio)
                    .addComponent(tfpatrimonio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        superior.add(infdatos);

        getContentPane().add(superior, java.awt.BorderLayout.PAGE_START);

        inferior.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inferior.setLayout(new java.awt.GridLayout(1, 0));

        icfirma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icfirma.setText("Firma");

        javax.swing.GroupLayout icentroLayout = new javax.swing.GroupLayout(icentro);
        icentro.setLayout(icentroLayout);
        icentroLayout.setHorizontalGroup(
            icentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(icentroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(icentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icfirma, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                    .addComponent(tffirma))
                .addContainerGap())
        );
        icentroLayout.setVerticalGroup(
            icentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(icentroLayout.createSequentialGroup()
                .addComponent(icfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tffirma, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addContainerGap())
        );

        inferior.add(icentro);

        iitecnico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iitecnico.setText("Tecnico");

        javax.swing.GroupLayout iizqLayout = new javax.swing.GroupLayout(iizq);
        iizq.setLayout(iizqLayout);
        iizqLayout.setHorizontalGroup(
            iizqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iitecnico, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
            .addGroup(iizqLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(tftecnico))
        );
        iizqLayout.setVerticalGroup(
            iizqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iizqLayout.createSequentialGroup()
                .addComponent(iitecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tftecnico)
                .addContainerGap())
        );

        inferior.add(iizq);

        idaclaracion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        idaclaracion.setText("Aclaracion");

        javax.swing.GroupLayout iderLayout = new javax.swing.GroupLayout(ider);
        ider.setLayout(iderLayout);
        iderLayout.setHorizontalGroup(
            iderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(iderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(iderLayout.createSequentialGroup()
                        .addComponent(idaclaracion, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                        .addGap(220, 220, 220))
                    .addGroup(iderLayout.createSequentialGroup()
                        .addComponent(tfaclaracion)
                        .addContainerGap())))
        );
        iderLayout.setVerticalGroup(
            iderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iderLayout.createSequentialGroup()
                .addComponent(idaclaracion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfaclaracion, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addContainerGap())
        );

        inferior.add(ider);

        getContentPane().add(inferior, java.awt.BorderLayout.PAGE_END);

        derecha.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        derecha.setPreferredSize(new java.awt.Dimension(150, 185));

        cbtareas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbtareasActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("TRABAJO REALIZADO");

        javax.swing.GroupLayout derechaLayout = new javax.swing.GroupLayout(derecha);
        derecha.setLayout(derechaLayout);
        derechaLayout.setHorizontalGroup(
            derechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, derechaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(derechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbtareas, 0, 116, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        derechaLayout.setVerticalGroup(
            derechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(derechaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(cbtareas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        getContentPane().add(derecha, java.awt.BorderLayout.LINE_END);

        izquierda.setPreferredSize(new java.awt.Dimension(150, 185));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("COMPONENTES");

        cbComponentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbComponentesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout izquierdaLayout = new javax.swing.GroupLayout(izquierda);
        izquierda.setLayout(izquierdaLayout);
        izquierdaLayout.setHorizontalGroup(
            izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(izquierdaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(cbComponentes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        izquierdaLayout.setVerticalGroup(
            izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(izquierdaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cbComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        getContentPane().add(izquierda, java.awt.BorderLayout.LINE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfdependenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfdependenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfdependenciaActionPerformed

    private void tffechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tffechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tffechaActionPerformed

    private void tfpatrimonioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfpatrimonioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfpatrimonioActionPerformed

    private void cbtareasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbtareasActionPerformed
        // TODO add your handling code here:
        this.tatareas.append((String) cbtareas.getSelectedItem()+" -");
    }//GEN-LAST:event_cbtareasActionPerformed

    private void cbComponentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbComponentesActionPerformed
        // TODO add your handling code here:
         this.tacomponentes.append((String) cbComponentes.getSelectedItem()+" -");
    }//GEN-LAST:event_cbComponentesActionPerformed

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
            java.util.logging.Logger.getLogger(FichaTecnicaImpresion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FichaTecnicaImpresion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FichaTecnicaImpresion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FichaTecnicaImpresion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FichaTecnicaImpresion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbComponentes;
    private javax.swing.JComboBox<String> cbtareas;
    private javax.swing.JPanel centro;
    private javax.swing.JPanel derecha;
    private javax.swing.JPanel icentro;
    private javax.swing.JLabel icfirma;
    private javax.swing.JLabel idaclaracion;
    private javax.swing.JPanel ider;
    private javax.swing.JLabel iitecnico;
    private javax.swing.JPanel iizq;
    private javax.swing.JPanel infdatos;
    private javax.swing.JPanel inferior;
    private javax.swing.JPanel inferiorcomponentes;
    private javax.swing.JPanel izquierda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lcomponentes;
    private javax.swing.JLabel ldependencia;
    private javax.swing.JLabel ldir;
    private javax.swing.JLabel llogo;
    private javax.swing.JLabel lnombre;
    private javax.swing.JLabel lpatrimonio;
    private javax.swing.JLabel ltareas;
    private javax.swing.JLabel ltel;
    private javax.swing.JLabel ltel2;
    private javax.swing.JLabel ltitulo;
    private javax.swing.JPanel superdatos;
    private javax.swing.JPanel superiologo;
    private javax.swing.JPanel superior;
    private javax.swing.JPanel superiortareas;
    private javax.swing.JPanel superlogosolo;
    private javax.swing.JTextArea tacomponentes;
    private javax.swing.JTextArea tatareas;
    private javax.swing.JTextField tfaclaracion;
    private javax.swing.JTextField tfdependencia;
    private javax.swing.JTextField tffecha;
    private javax.swing.JTextField tffirma;
    private javax.swing.JTextField tfpatrimonio;
    private javax.swing.JTextField tftecnico;
    // End of variables declaration//GEN-END:variables

    
}
