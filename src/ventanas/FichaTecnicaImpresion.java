/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import auxiliar.DocumentSizeFilter;
import auxiliar.GestorArchivo;
import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import ca.odell.glazedlists.swing.DefaultEventComboBoxModel;
import static fichatecnica.FichaTecnica.NOMBRE_APP;
import static fichatecnica.FichaTecnica.NOMBRE_ARCHIVOS;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AbstractDocument;
import reporte.Reporte;

/**
 *
 * @author jsilva
 */
public class FichaTecnicaImpresion extends javax.swing.JFrame {
    private Mail vtnCorreo;
    private List<String> lstTareas, lstComponentes, lstDependencias,lstCampos;
    private final String separadorCampos=NOMBRE_ARCHIVOS.getProperty("separadorCampos");
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
              //configuraciones= new JMenu("Configuraciones"),
              ayuda= new JMenu("Ayuda");
        mb.add(archivo);
        mb.add(acciones);
        //mb.add(configuraciones);
        mb.add(ayuda);
        
        JMenuItem salir= new JMenuItem("Salir"),
                nuevo= new JMenuItem("Nueva Ficha"),
                imprimir= new JMenuItem("Imprimir"),
                enviar= new JMenuItem("Enviar"),
                abrir= new JMenuItem("Abrir"),
                guardar= new JMenuItem("Guardar"),
                //configuracion= new JMenuItem("Configuracion"),
                acercaDe=new JMenuItem("Acerca de...");
        
        abrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrir();
            }
        });
        
        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardar();
            }
        });
        
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
                JOptionPane.showMessageDialog(null, NOMBRE_APP+"\nDesarrollado por:\nT.S.P. Silva, Jonatan\nA.U.S. Peralta, Cesar", 
                        "Acerca de:", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
        /*
        configuracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearVentanaConfiguraciones();
            }
        });
        configuraciones.add(configuracion);*/
        
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
        String e=NOMBRE_ARCHIVOS.getProperty("enviar"), 
               i=NOMBRE_ARCHIVOS.getProperty("imprimir"), 
               n=NOMBRE_ARCHIVOS.getProperty("nuevo");
        
        JButton btnEnviar= new JButton();
        JButton btnImprimir= new JButton();
        JButton btnNuevo= new JButton();
        
        ImageIcon ii= crearImageIcon(e, "");
        btnEnviar.setIcon(ii);
        
       ii= crearImageIcon(i, "");
       btnImprimir.setIcon(ii);
        
        ii= crearImageIcon(n, "");
        btnNuevo.setIcon(ii);
        
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
        String logo= NOMBRE_ARCHIVOS.getProperty("logoApp"), 
               ic=NOMBRE_ARCHIVOS.getProperty("iconoApp");

        llogo.setIcon(crearImageIcon(logo, ""));
        superlogosolo.add(llogo);
                       
        setIconImage(crearImageIcon(ic, "").getImage());
        
        this.tffecha.setEditable(false);
        int maxChars;        
        try{
            maxChars = Integer.valueOf(NOMBRE_ARCHIVOS.getProperty("cantCaracteresComboBox"));
        }catch(NumberFormatException e){
            System.err.println(e.getLocalizedMessage());
            maxChars= 100;
        }
        this.setTitle(NOMBRE_APP);
        AbstractDocument pDoc=(AbstractDocument)tatareas.getDocument();        
        pDoc.setDocumentFilter(new DocumentSizeFilter(maxChars));
        
        pDoc=(AbstractDocument)tacomponentes.getDocument();        
        pDoc.setDocumentFilter(new DocumentSizeFilter(maxChars));
    }
    
    private void inicializarValoresDesdeArchivo() throws FileNotFoundException {
        String tareas=NOMBRE_ARCHIVOS.getProperty("tareas"), 
               componentes=NOMBRE_ARCHIVOS.getProperty("componentes"),
               dependencias=NOMBRE_ARCHIVOS.getProperty("dependencias");

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
        tffecha.setText(new SimpleDateFormat(NOMBRE_ARCHIVOS.getProperty("formatoFecha")).format(actual));
        
        tftecnico.setText(System.getProperty("user.name"));
    }
    
    private void cargarLista(List<String> lstDatos, String archivoDatos) throws FileNotFoundException{
        String line;
        File archivo = GestorArchivo.cargarArchivo(archivoDatos, "/");
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
    
    protected ImageIcon crearImageIcon(String path,
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
        
        try{
        if(vtnCorreo == null)
            vtnCorreo= new Mail(this, true);
        
        vtnCorreo.asunto(tfpatrimonio.getText());
        vtnCorreo.datos(sb.toString());
        vtnCorreo.setLocationRelativeTo(null);
        vtnCorreo.setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Se ha producido un error en el envio de archivo: "+e.getLocalizedMessage(), 
                        "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private StringBuilder cargarListaCampos(){
        StringBuilder sb= new StringBuilder();
        
        String aux= cbdependencia.getSelectedItem()==null?"":cbdependencia.getSelectedItem().toString();
        sb.append(aux);
        sb.append(separadorCampos);
        sb.append(tffecha.getText());
        sb.append(separadorCampos);
        sb.append(tfpatrimonio.getText());
        sb.append(separadorCampos);
        sb.append(tatareas.getText());
        sb.append(separadorCampos);
        sb.append(tacomponentes.getText());
        sb.append(separadorCampos);
        sb.append(tftecnico.getText());
        sb.append(separadorCampos);
        
        return sb;
    }
    
    private void abrir(){
        JFileChooser fc = getJFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            File fichero = fc.getSelectedFile();
            System.out.println("Abrir: "+fichero);
            try{
                String aux;
                String [] aux2;
                Scanner scnr = new Scanner(fichero);
                while(scnr.hasNextLine()){
                    aux= scnr.nextLine();
                    System.out.println(aux);
                    aux2= aux.split(separadorCampos);
                    cbdependencia.setSelectedItem(aux2[0]);
                    tffecha.setText(aux2[1]);
                    tfpatrimonio.setText(aux2[2]);
                    tatareas.setText(aux2[3]);
                    tacomponentes.setText(aux2[4]);
                    tftecnico.setText(aux2[5]);
                }
            }catch(Exception e){
                System.err.println(e.getLocalizedMessage());
                e.printStackTrace();
            }
            
        }
    }
    
    private void guardar(){
        JFileChooser fc = getJFileChooser();
        int returnVal = fc.showSaveDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            File fichero = fc.getSelectedFile();
            System.out.println("Guardar: "+fichero);
            BufferedWriter bw;
            String ext= NOMBRE_ARCHIVOS.getProperty("extArchivoFichaTecnica");
            try{
                if(fichero.getAbsolutePath().endsWith(ext)){
                    bw = new BufferedWriter(new FileWriter(fichero));
                }else{
                    bw = new BufferedWriter(new FileWriter(fichero+"."+ext));
                }                
                bw.write(cargarListaCampos().toString());
                bw.close();
            }catch(Exception e){
                System.err.println(e.getLocalizedMessage());
                e.printStackTrace();
            }            
        }
    }
    
    private void crearVentanaConfiguraciones(){
        Configuraciones c= new Configuraciones(this, true);
        c.setVisible(true);
    }
    
    private JFileChooser getJFileChooser(){
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                NOMBRE_ARCHIVOS.getProperty("descArchivoFichaTecnica"), 
                NOMBRE_ARCHIVOS.getProperty("extArchivoFichaTecnica"));
        fc.setFileFilter(filter);
        return fc;
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
        pnlTareasDisponibles = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbtareas = new javax.swing.JComboBox<>();
        ltareas = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tatareas = new javax.swing.JTextArea();
        inferiorcomponentes = new javax.swing.JPanel();
        pnlComponentes = new javax.swing.JPanel();
        cbComponentes = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        centro.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        centro.setPreferredSize(new java.awt.Dimension(990, 342));
        centro.setLayout(new java.awt.GridLayout(2, 1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("TRABAJO REALIZADO");

        cbtareas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbtareasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTareasDisponiblesLayout = new javax.swing.GroupLayout(pnlTareasDisponibles);
        pnlTareasDisponibles.setLayout(pnlTareasDisponiblesLayout);
        pnlTareasDisponiblesLayout.setHorizontalGroup(
            pnlTareasDisponiblesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTareasDisponiblesLayout.createSequentialGroup()
                .addContainerGap(124, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnlTareasDisponiblesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTareasDisponiblesLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(cbtareas, 0, 228, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlTareasDisponiblesLayout.setVerticalGroup(
            pnlTareasDisponiblesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTareasDisponiblesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(122, Short.MAX_VALUE))
            .addGroup(pnlTareasDisponiblesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlTareasDisponiblesLayout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(cbtareas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(87, Short.MAX_VALUE)))
        );

        ltareas.setText("Se Realizo:");

        tatareas.setColumns(20);
        tatareas.setLineWrap(true);
        tatareas.setRows(5);
        jScrollPane2.setViewportView(tatareas);

        javax.swing.GroupLayout superiortareasLayout = new javax.swing.GroupLayout(superiortareas);
        superiortareas.setLayout(superiortareasLayout);
        superiortareasLayout.setHorizontalGroup(
            superiortareasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(superiortareasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ltareas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1072, Short.MAX_VALUE)
                .addComponent(pnlTareasDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(superiortareasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(superiortareasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE)
                    .addGap(263, 263, 263)))
        );
        superiortareasLayout.setVerticalGroup(
            superiortareasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(superiortareasLayout.createSequentialGroup()
                .addGroup(superiortareasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(superiortareasLayout.createSequentialGroup()
                        .addComponent(ltareas)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(superiortareasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlTareasDisponibles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(superiortareasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, superiortareasLayout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        centro.add(superiortareas);

        cbComponentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbComponentesActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("COMPONENTES");

        javax.swing.GroupLayout pnlComponentesLayout = new javax.swing.GroupLayout(pnlComponentes);
        pnlComponentes.setLayout(pnlComponentesLayout);
        pnlComponentesLayout.setHorizontalGroup(
            pnlComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlComponentesLayout.createSequentialGroup()
                .addGap(0, 171, Short.MAX_VALUE)
                .addComponent(jLabel2))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlComponentesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbComponentes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlComponentesLayout.setVerticalGroup(
            pnlComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlComponentesLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lcomponentes.setText("Componentes utilizados:");

        tacomponentes.setColumns(20);
        tacomponentes.setLineWrap(true);
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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1121, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        inferiorcomponentesLayout.setVerticalGroup(
            inferiorcomponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inferiorcomponentesLayout.createSequentialGroup()
                .addGroup(inferiorcomponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(inferiorcomponentesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlComponentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(inferiorcomponentesLayout.createSequentialGroup()
                        .addComponent(lcomponentes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, superdatosLayout.createSequentialGroup()
                .addContainerGap(113, Short.MAX_VALUE)
                .addComponent(ltel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(superdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(superdatosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(superdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ldir, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ltel, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        superdatosLayout.setVerticalGroup(
            superdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, superdatosLayout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(ltel2)
                .addGap(31, 31, 31))
            .addGroup(superdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(superdatosLayout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(lnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(ldir)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(ltel)
                    .addContainerGap(31, Short.MAX_VALUE)))
        );

        superiologo.add(superdatos);

        superior.add(superiologo);

        ltitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ltitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ltitulo.setText("SOPORTE TECNICO");

        ldependencia.setText("Dependencia:");

        jLabel3.setText("Fecha:");

        lpatrimonio.setText("Nº Patrimonio");

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
                                .addComponent(cbdependencia, 0, 1073, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tffecha, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfpatrimonio)))
                    .addGroup(infdatosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ltitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        infdatosLayout.setVerticalGroup(
            infdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infdatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ltitulo)
                .addGap(10, 10, 10)
                .addGroup(infdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ldependencia)
                    .addComponent(jLabel3)
                    .addComponent(tffecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbdependencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap())
        );

        inferior.add(iizq);

        getContentPane().add(inferior, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JLabel iitecnico;
    private javax.swing.JPanel iizq;
    private javax.swing.JPanel infdatos;
    private javax.swing.JPanel inferior;
    private javax.swing.JPanel inferiorcomponentes;
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
    private javax.swing.JPanel pnlComponentes;
    private javax.swing.JPanel pnlTareasDisponibles;
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
