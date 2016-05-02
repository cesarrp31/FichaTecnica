/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import ca.odell.glazedlists.swing.DefaultEventComboBoxModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import reporte.Reporte;

/**
 *
 * @author jsilva
 */
public class FichaTecnicaImpresion extends javax.swing.JFrame {
    private Mail vtnCorreo;
    private List<String> lstTareas, lstComponentes, lstDependencias;
    
    /**
     * Creates new form FichaTecnicaImpresion
     * @throws java.io.FileNotFoundException
     */
    public FichaTecnicaImpresion() throws FileNotFoundException {
        initComponents();
        
        inicializarMenu();        
        inicializarValoresDesdeArchivo();
        inicializarPantallaCarga();        
        cargarValoresComboBox();        
        inicializarValoresEstaticos();
        
        inicializarBarraHerramientas();
    }
    
    private void inicializarMenu(){
        JMenuBar mb= new JMenuBar();
        
        JMenu archivo= new JMenu("Archivo"),
              acciones= new JMenu("Acciones"),
              ayuda= new JMenu("Ayuda");
        mb.add(archivo);
        mb.add(acciones);
        mb.add(ayuda);
        
        JMenuItem salir= new JMenuItem("Salir"),
                nuevo= new JMenuItem("Nueva Ficha"),
                imprimir= new JMenuItem("Imprimir"),
                enviar= new JMenuItem("Enviar"),
                abrir= new JMenuItem("Abrir"),
                guardar= new JMenuItem("Guardar"),
                acercaDe=new JMenuItem("Acerca de...");
        
        nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicializarPantallaCarga();
            }
        });
        
        imprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimir();
            }
        });
        
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviar();
            }
        });
        
        acercaDe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Sistema de Creación de Fichas Técnicas V1.0\nHecho por:\nT.S.P. Silva, Jonatan\nA.U.S. Peralta, Cesar", 
                        "Acerca de:", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
        
        archivo.add(nuevo);
        archivo.addSeparator();
        archivo.add(abrir);
        archivo.add(guardar);
        archivo.addSeparator();
        archivo.add(imprimir);
        archivo.addSeparator();
        acciones.add(enviar);
        archivo.add(salir);
        ayuda.add(acercaDe);
        
        this.setJMenuBar(mb);
    }
    
    private void inicializarBarraHerramientas(){
        JToolBar barraHerramientas = new JToolBar();
        
        JButton btnEnviar= new JButton("Enviar");
        JButton btnImprimir= new JButton("Imprimir");
        JButton btnNuevo= new JButton("Nuevo");
        
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviar();
            }
        });
        
        btnImprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimir();
            }
        });
        
        btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicializarPantallaCarga();
            }
        });
        
        barraHerramientas.add(btnNuevo);
        barraHerramientas.add(btnImprimir);
        barraHerramientas.add(btnEnviar);
        
        JPanel pnAnterior= (JPanel) this.getContentPane(),
               pnNuevo= new JPanel();
        pnNuevo.setLayout(new BorderLayout());
        pnNuevo.add(pnAnterior, BorderLayout.CENTER);
        pnNuevo.add(barraHerramientas, BorderLayout.NORTH);
        this.setContentPane(pnNuevo);        
    }
    
    private void inicializarValoresEstaticos(){
        String logo= "logo_camara.png", ic="iconoApp.png";

        ImageIcon ii= createImageIcon(logo, "");                        
        llogo.setIcon(ii);
        superlogosolo.add(llogo);
        
        ii= createImageIcon(ic, "");
        setIconImage(ii.getImage());
        
        this.tffecha.setEditable(false);
    }
    
    private void inicializarValoresDesdeArchivo() throws FileNotFoundException {
        String tareas="tareas.csv", componentes="componentes.csv",
               dependencias="dependencias.csv";

        lstTareas= new ArrayList<>();
        lstComponentes= new ArrayList<>();
        lstDependencias= new ArrayList<>();

        cargarLista(lstTareas, tareas);     
        cargarLista(lstComponentes, componentes);
        cargarLista(lstDependencias, dependencias);       
    }
    
    private void inicializarPantallaCarga() {        
        cbComponentes.setSelectedItem(null);
        cbtareas.setSelectedItem(null);
        cbdependencia.setSelectedItem(null);
        
        this.tfpatrimonio.setText("");
        this.tatareas.setText("");
        this.tacomponentes.setText("");
        Date actual=new Date();
        tffecha.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(actual));
    }
    
    private void cargarLista(List<String> lstDatos, String archivoDatos) throws FileNotFoundException{
        String line;
        File archivo = new File(this.getClass().getClassLoader().getResource(archivoDatos).getFile());
        Scanner scnr = new Scanner(archivo);            
        while(scnr.hasNextLine()){
            line = scnr.nextLine();
            lstDatos.add(line);
        }
    }
    
    private void cargarValoresComboBox(){
        cargarValores(cbtareas, lstTareas);        
        cargarValores(cbComponentes, lstComponentes);
        cargarValores(cbdependencia, lstDependencias);
    }
    
    private void cargarValores(JComboBox cb, List<String> lstDatos){
        SortedList<String> datos = new SortedList<String>(new BasicEventList<String>());
        for(String dato: lstDatos){
            datos.add(dato);
        }
        
        DefaultEventComboBoxModel<String> modelo = new DefaultEventComboBoxModel<String>(datos);
        cb.setModel(modelo);
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AutoCompleteSupport autocomplete = AutoCompleteSupport.install(cb, datos);
                autocomplete.setFilterMode(TextMatcherEditor.CONTAINS);
            }
        });
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
    
    private void salir(){
        this.dispose();
    }
    
    private void imprimir(){
        Object dep= cbdependencia.getSelectedItem();
        Reporte.crearReporte(tatareas.getText(), tacomponentes.getText(), (dep==null?"":dep.toString()), 
                            tfpatrimonio.getText(), tftecnico.getText(), tffecha.getText(), this);
    }
    
    private void enviar(){
        String salto="\n";
        StringBuilder sb= new StringBuilder();
        sb.append("Dependencia: ");
        sb.append(cbdependencia.getSelectedItem());
        sb.append(salto);
        sb.append("Fecha: ");
        sb.append(tffecha.getText());
        sb.append(salto);
        sb.append("Patrimonio/s: ");
        sb.append(tfpatrimonio.getText());
        sb.append(salto);
        sb.append(salto);
        if(!tatareas.getText().isEmpty()){
            sb.append("Tareas Realizadas");
            sb.append(salto);
            sb.append(tatareas.getText());
            sb.append(salto);
            sb.append(salto);
        }
        if(!tacomponentes.getText().isEmpty()){
            sb.append("Componentes Utilizados");
            sb.append(salto);
            sb.append(tacomponentes.getText());
            sb.append(salto);
            sb.append(salto);
        }
        sb.append("Tecnico/s: ");
        sb.append(tftecnico.getText());
        
        if(vtnCorreo == null)
            vtnCorreo= new Mail(this, true);
        
        vtnCorreo.asunto("Actualización de estado de: "+tfpatrimonio.getText());
        vtnCorreo.datos(sb.toString());
        vtnCorreo.setLocationRelativeTo(null);
        vtnCorreo.setVisible(true);
    }
    
    private void nuevo(){
        inicializarPantallaCarga();
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
        tffecha = new javax.swing.JTextField();
        lpatrimonio = new javax.swing.JLabel();
        tfpatrimonio = new javax.swing.JTextField();
        cbdependencia = new javax.swing.JComboBox<>();
        inferior = new javax.swing.JPanel();
        iizq = new javax.swing.JPanel();
        iitecnico = new javax.swing.JLabel();
        tftecnico = new javax.swing.JTextField();
        derecha = new javax.swing.JPanel();
        cbtareas = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        izquierda = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbComponentes = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

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
                .addContainerGap(930, Short.MAX_VALUE))
            .addGroup(superiortareasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(superiortareasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        superiortareasLayout.setVerticalGroup(
            superiortareasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(superiortareasLayout.createSequentialGroup()
                .addComponent(ltareas)
                .addContainerGap(143, Short.MAX_VALUE))
            .addGroup(superiortareasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, superiortareasLayout.createSequentialGroup()
                    .addContainerGap(24, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
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
                        .addGap(0, 856, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        inferiorcomponentesLayout.setVerticalGroup(
            inferiorcomponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inferiorcomponentesLayout.createSequentialGroup()
                .addComponent(lcomponentes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
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
                                .addComponent(cbdependencia, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tffecha, javax.swing.GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE))
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
                    .addComponent(jLabel3)
                    .addComponent(tffecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbdependencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(infdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lpatrimonio)
                    .addComponent(tfpatrimonio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        superior.add(infdatos);

        getContentPane().add(superior, java.awt.BorderLayout.PAGE_START);

        inferior.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inferior.setLayout(new java.awt.GridLayout(1, 0));

        iitecnico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iitecnico.setText("Tecnico");

        javax.swing.GroupLayout iizqLayout = new javax.swing.GroupLayout(iizq);
        iizq.setLayout(iizqLayout);
        iizqLayout.setHorizontalGroup(
            iizqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iizqLayout.createSequentialGroup()
                .addComponent(iitecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tftecnico, javax.swing.GroupLayout.DEFAULT_SIZE, 1319, Short.MAX_VALUE)
                .addContainerGap())
        );
        iizqLayout.setVerticalGroup(
            iizqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iizqLayout.createSequentialGroup()
                .addGroup(iizqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(iitecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tftecnico))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        inferior.add(iizq);

        getContentPane().add(inferior, java.awt.BorderLayout.PAGE_END);

        derecha.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        derecha.setPreferredSize(new java.awt.Dimension(250, 185));
        derecha.setVerifyInputWhenFocusTarget(false);

        cbtareas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbtareasActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("TRABAJO REALIZADO");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/índice.png"))); // NOI18N

        javax.swing.GroupLayout derechaLayout = new javax.swing.GroupLayout(derecha);
        derecha.setLayout(derechaLayout);
        derechaLayout.setHorizontalGroup(
            derechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, derechaLayout.createSequentialGroup()
                .addGroup(derechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, derechaLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                    .addComponent(cbtareas, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        derechaLayout.setVerticalGroup(
            derechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(derechaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(cbtareas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(derecha, java.awt.BorderLayout.LINE_END);

        izquierda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        izquierda.setPreferredSize(new java.awt.Dimension(150, 185));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("COMPONENTES");

        cbComponentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbComponentesActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/thumb_COLOURBOX6192513.jpg"))); // NOI18N

        javax.swing.GroupLayout izquierdaLayout = new javax.swing.GroupLayout(izquierda);
        izquierda.setLayout(izquierdaLayout);
        izquierdaLayout.setHorizontalGroup(
            izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(izquierdaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, izquierdaLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(izquierdaLayout.createSequentialGroup()
                        .addGroup(izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        izquierdaLayout.setVerticalGroup(
            izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(izquierdaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cbComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addGroup(izquierdaLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(izquierda, java.awt.BorderLayout.LINE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            @Override
            public void run() {
                try {
                    new FichaTecnicaImpresion().setVisible(true);
                } catch (FileNotFoundException ex) {
                    System.err.println("Error: "+ex.getLocalizedMessage());
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbComponentes;
    private javax.swing.JComboBox<String> cbdependencia;
    private javax.swing.JComboBox<String> cbtareas;
    private javax.swing.JPanel centro;
    private javax.swing.JPanel derecha;
    private javax.swing.JLabel iitecnico;
    private javax.swing.JPanel iizq;
    private javax.swing.JPanel infdatos;
    private javax.swing.JPanel inferior;
    private javax.swing.JPanel inferiorcomponentes;
    private javax.swing.JPanel izquierda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JTextField tffecha;
    private javax.swing.JTextField tfpatrimonio;
    private javax.swing.JTextField tftecnico;
    // End of variables declaration//GEN-END:variables

}
