/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import auxiliar.CodigoQR;
import auxiliar.DocumentSizeFilter;
import auxiliar.GestorArchivo;
import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import ca.odell.glazedlists.swing.DefaultEventComboBoxModel;
import datos.DatosFichaTecnica;
import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import reporte.Reporte;
import static fichatecnica.FichaTecnica.CONFIG_GENERAL;
import static fichatecnica.FichaTecnica.NOMBRE_APP;
import auxiliar.IGestionArchivo;
import auxiliar.IGestorLectorArchivoTexto;
import java.awt.Frame;
import java.io.IOException;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author jsilva
 */
public class FichaTecnicaImpresion extends javax.swing.JFrame implements IGestionArchivo, IGestorLectorArchivoTexto{

    private Mail vtnCorreo;
    private List<String> lstTareas, lstComponentes, lstDependencias, lstTemp, lstEstados;
    private final String crpImg = CONFIG_GENERAL.getProperty("crp.imagenes") + GestorArchivo.SEPARADOR,
            crpRec = CONFIG_GENERAL.getProperty("crp.recursos") + GestorArchivo.SEPARADOR;
    public static final String DELIMITADOR = CONFIG_GENERAL.getProperty("conf.separadorCampos");

    /**
     * Creates new form FichaTecnicaImpresion
     *
     * @throws java.io.FileNotFoundException
     */
    public FichaTecnicaImpresion() throws FileNotFoundException, IOException {
        initComponents();

        inicializarComponentes();
        inicializarMenu();
        inicializarValoresDesdeArchivo();
        inicializarPantallaCarga();
        cargarValoresComboBox();
        inicializarValoresEstaticos();

        inicializarBarraHerramientas();
    }

    private void inicializarComponentes() {
        inicializarComboBox(cbComponentes);
        inicializarComboBox(cbdependencia);
        inicializarComboBox(cbtareas);     
    }

    private void inicializarMenu() {
        JMenuBar mb = new JMenuBar();

        JMenu archivo = new JMenu("Archivo"),
                acciones = new JMenu("Acciones"),
                configuraciones= new JMenu("Configuraciones"),
                ayuda = new JMenu("Ayuda");
        mb.add(archivo);
        mb.add(acciones);
        mb.add(configuraciones);
        mb.add(ayuda);

        JMenuItem salir = new JMenuItem("Salir"),
                nuevo = new JMenuItem("Nueva Ficha"),
                imprimir = new JMenuItem("Imprimir"),
                enviar = new JMenuItem("Enviar"),
                abrir = new JMenuItem("Abrir"),
                guardar = new JMenuItem("Guardar"),
                configuracion= new JMenuItem("Configuracion"),
                acercaDe = new JMenuItem("Acerca de...");

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
                JOptionPane.showMessageDialog(null, NOMBRE_APP + "\nDiseñado y Desarrollado por:\nT.A.E. Gallo, Mario\nT.S.P. Silva, Jonatan\nA.U.S. Peralta, Cesar",
                        "Acerca de:", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
        
        configuracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearVentanaConfiguraciones();
            }
        });
        configuraciones.add(configuracion);

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

    private void inicializarBarraHerramientas() throws FileNotFoundException {
        JToolBar barraHerramientas = new JToolBar();
        String e = crpImg + CONFIG_GENERAL.getProperty("btn.imag.enviar"),
                i = crpImg + CONFIG_GENERAL.getProperty("btn.imag.imprimir"),
                n = crpImg + CONFIG_GENERAL.getProperty("btn.imag.nuevo"),
                g = crpImg + CONFIG_GENERAL.getProperty("btn.imag.guardar"),
                a = crpImg + CONFIG_GENERAL.getProperty("btn.imag.abrir");

        JButton btnEnviar = new JButton(),
                btnImprimir = new JButton(),
                btnNuevo = new JButton(),
                btnGuardar = new JButton(),
                btnAbrir = new JButton();

        ImageIcon ii = GestorArchivo.crearImageIcon(e, "");
        btnEnviar.setIcon(ii);

        ii = GestorArchivo.crearImageIcon(i, "");
        btnImprimir.setIcon(ii);

        ii = GestorArchivo.crearImageIcon(n, "");
        btnNuevo.setIcon(ii);

        ii = GestorArchivo.crearImageIcon(a, "");
        btnAbrir.setIcon(ii);

        ii = GestorArchivo.crearImageIcon(g, "");
        btnGuardar.setIcon(ii);

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

        btnAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrir();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardar();
            }
        });

        barraHerramientas.add(btnNuevo);
        barraHerramientas.add(btnAbrir);
        barraHerramientas.addSeparator();
        barraHerramientas.add(btnGuardar);
        barraHerramientas.add(btnImprimir);
        barraHerramientas.addSeparator();
        barraHerramientas.add(btnEnviar);

        btnEnviar.setToolTipText("Enviar por Correo");
        btnImprimir.setToolTipText("Imprimir");
        btnNuevo.setToolTipText("Nueva Ficha");
        btnGuardar.setToolTipText("Guardar Ficha");
        btnAbrir.setToolTipText("Abrir Ficha");

        JPanel pnAnterior = (JPanel) this.getContentPane(),
                pnNuevo = new JPanel();
        pnNuevo.setLayout(new BorderLayout());
        pnNuevo.add(pnAnterior, BorderLayout.CENTER);
        pnNuevo.add(barraHerramientas, BorderLayout.NORTH);
        this.setContentPane(pnNuevo);
    }

    private void inicializarValoresEstaticos() throws FileNotFoundException {
        String logo = crpImg + CONFIG_GENERAL.getProperty("imag.logoApp"),
                ic = crpImg + CONFIG_GENERAL.getProperty("imag.iconoApp");

        llogo.setIcon(GestorArchivo.crearImageIcon(logo, ""));
        superlogosolo.add(llogo);

        setIconImage(GestorArchivo.crearImageIcon(ic, "").getImage());

        this.tffecha.setEditable(false);
        int maxChars;
        try {
            maxChars = Integer.valueOf(CONFIG_GENERAL.getProperty("conf.cantCaracteresComboBox"));
        } catch (NumberFormatException e) {
            System.err.println(e.getLocalizedMessage());
            maxChars = 100;
        }
        this.setTitle(NOMBRE_APP);
        AbstractDocument pDoc = (AbstractDocument) tatareas.getDocument();
        pDoc.setDocumentFilter(new DocumentSizeFilter(maxChars));

        pDoc = (AbstractDocument) tacomponentes.getDocument();
        pDoc.setDocumentFilter(new DocumentSizeFilter(maxChars));
    }

    private void inicializarValoresDesdeArchivo() throws FileNotFoundException, IOException {
        String tareas = CONFIG_GENERAL.getProperty("val.tareas"),
                componentes = CONFIG_GENERAL.getProperty("val.componentes"),
                dependencias = CONFIG_GENERAL.getProperty("val.dependencias"),
                estados= CONFIG_GENERAL.getProperty("val.estados");

        lstTareas = new ArrayList<>();
        lstComponentes = new ArrayList<>();
        lstDependencias = new ArrayList<>();
        lstEstados = new ArrayList<>();

        cargarLista(lstTareas, tareas);
        cargarLista(lstComponentes, componentes);
        cargarLista(lstDependencias, dependencias);
        cargarLista(lstEstados, estados);
    }

    private void inicializarPantallaCarga() {
        cbComponentes.setSelectedItem(null);
        cbtareas.setSelectedItem(null);
        cbdependencia.setSelectedItem(null);

        this.tfpatrimonio.setText("");
        this.tatareas.setText("");
        this.tacomponentes.setText("");
        Date actual = new Date();
        tffecha.setText(new SimpleDateFormat(CONFIG_GENERAL.getProperty("conf.formatoFecha")).format(actual));

        boolean cargarUsuarioSesion= Boolean.valueOf(CONFIG_GENERAL.getProperty("default.appNom.cargarSesion"));
        if(cargarUsuarioSesion)
            tftecnico.setText(System.getProperty("user.name"));
        else
            tftecnico.setText(CONFIG_GENERAL.getProperty("default.appNom.tecnico"));
    }

    private void cargarLista(List<String> lstDatos, String archivoDatos) throws FileNotFoundException, IOException {
        lstTemp= lstDatos;
        GestorArchivo.obtenerPropiedades(crpRec + archivoDatos, this);
    }
    
    @Override
    public void agregarLinea(String linea) {
        lstTemp.add(linea);
    }

    private void cargarValoresComboBox() {
        cargarValores(cbtareas, lstTareas);
        cargarValores(cbComponentes, lstComponentes);
        cargarValores(cbdependencia, lstDependencias);
        
        cbEstados.setModel(new DefaultComboBoxModel<String>(new Vector(lstEstados)));
    }

    private void cargarValores(JComboBox cb, List<String> lstDatos) {
        SortedList<String> datos = new SortedList<String>(new BasicEventList<String>());
        for (String dato : lstDatos) {
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

    private void salir() {
        this.dispose();
    }

    private void imprimir() {
        DatosFichaTecnica dft;
        if(controlCamposCompletados()){
            dft= this.getDatosFichaTecnica();
            generarCodigoQR(dft);

            Reporte.crearReporte(dft, this);
        }
    }

    private void inicializarComboBox(JComboBox cb) {
        ListCellRenderer comboRenderer = new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list,
                    Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                if (value instanceof String) {
                    setToolTipText(value.toString());
                    value = value.toString();
                } else {
                    setToolTipText(null);
                }
                return super.getListCellRendererComponent(list, value, index, isSelected,
                        cellHasFocus);
            }
        };
        cb.setRenderer(comboRenderer);
    }

    private void generarCodigoQR(DatosFichaTecnica dft) {
        String pathCompleto = CONFIG_GENERAL.getProperty("crp.temp")
                + GestorArchivo.SEPARADOR
                + CONFIG_GENERAL.getProperty("imag.codigoQR");
             
        CodigoQR qr= new CodigoQR();
        try {
            qr.crearCodigoQR(dft.toString(), pathCompleto);            
            System.out.println("Valor creado: \n"+qr.leerCodigoQR(pathCompleto));
        } catch (Exception ex) {
            System.err.println("Error: "+ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

    private void enviar() {
        if(controlCamposCompletados()){
            try {
                if (vtnCorreo == null) {
                    vtnCorreo = new Mail(this, true);
                }
                vtnCorreo.asunto(tfpatrimonio.getText());
                vtnCorreo.datos(this.getDatosFichaTecnica());
                vtnCorreo.setLocationRelativeTo(null);
                vtnCorreo.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Se ha producido un error en el envio de archivo: " + e.getLocalizedMessage(),
                        "ERROR!", JOptionPane.ERROR_MESSAGE);
            }
        }        
    }

    private boolean controlCamposCompletados(){
        boolean camposCompletados= true;
        String msg="";
        if((this.cbdependencia.getSelectedItem() == null)||
           (this.cbdependencia.getSelectedItem().toString().isEmpty())){
            msg= "Escriba una dependencia.\n";
        }
        if(this.tfpatrimonio.getText().isEmpty()){
            msg+="Escriba 1 o varios nro de patrimonios.\n";
        }
        
        if(this.tftecnico.getText().isEmpty()){
            msg+="Escriba 1 o varios nombres de técnicos.\n";
        }
        
        if(!msg.isEmpty()){
            JOptionPane.showMessageDialog(null, msg,
                    "Información!", JOptionPane.INFORMATION_MESSAGE);
            camposCompletados= false;
        }        
        return camposCompletados;
    }
    
    private DatosFichaTecnica getDatosFichaTecnica() {
        String dep = cbdependencia.getSelectedItem()==null?"":cbdependencia.getSelectedItem().toString();
        DatosFichaTecnica dft = new DatosFichaTecnica(dep, tffecha.getText(), tfpatrimonio.getText(),
                tatareas.getText(), tacomponentes.getText(), tftecnico.getText());
        return dft;
    }

    private StringBuilder cargarListaCampos() {
        StringBuilder sb = new StringBuilder();

        String aux = cbdependencia.getSelectedItem() == null ? "" : cbdependencia.getSelectedItem().toString();
        sb.append(aux);
        sb.append(DELIMITADOR);
        sb.append(tffecha.getText());
        sb.append(DELIMITADOR);
        sb.append(tfpatrimonio.getText());
        sb.append(DELIMITADOR);
        sb.append(tatareas.getText());
        sb.append(DELIMITADOR);
        sb.append(tacomponentes.getText());
        sb.append(DELIMITADOR);
        sb.append(tftecnico.getText());
        sb.append(DELIMITADOR);

        return sb;
    }

    private void  abrir(){
        GestorArchivo.abrirArchivo(this);
    }
    
    @Override
    public void abrir(File fichero) {
        System.out.println("Abrir: " + fichero);
        try {
            String aux;
            String[] aux2;
            Scanner scnr = new Scanner(fichero);
            while (scnr.hasNextLine()) {
                aux = scnr.nextLine();
                System.out.println(aux);
                aux2 = aux.split(DELIMITADOR);
                cbdependencia.setSelectedItem(aux2[0]);
                tffecha.setText(aux2[1]);
                tfpatrimonio.setText(aux2[2]);
                tatareas.setText(aux2[3]);
                tacomponentes.setText(aux2[4]);
                tftecnico.setText(aux2[5]);
            }
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            e.printStackTrace();
        }        
    }

    private void guardar(){
        GestorArchivo.guardar(this);
    }
    
    @Override
    public void guardar(File fichero) {        
        BufferedWriter bw;
        String ext = this.extensionArchivo();
        try {
            if (fichero.getPath().endsWith(ext)) {
                bw = new BufferedWriter(new FileWriter(fichero));
            } else {
                bw = new BufferedWriter(new FileWriter(fichero + "." + ext));
            }
            bw.write(cargarListaCampos().toString());
            bw.close();
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            e.printStackTrace();
        }        
    }
    
    @Override
    public Frame ventana(){
        return this;
    }
    
    @Override
    public String extensionArchivo(){
        return CONFIG_GENERAL.getProperty("conf.extArchivoFichaTecnica");
    }
    
    @Override
    public String descripcionTipoArchivo(){
        return CONFIG_GENERAL.getProperty("conf.descArchivoFichaTecnica");
    }
    
    @Override
    public String carpetaGuardado(){
        return CONFIG_GENERAL.getProperty("crp.guardado");
    }
    
    private void crearVentanaConfiguraciones() {
        Configuraciones c = new Configuraciones(this, true);
        c.setLocationRelativeTo(null);
        c.pack();
        c.setVisible(true);
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tatareas = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        pnlTareasDisponibles = new javax.swing.JPanel();
        cbtareas = new javax.swing.JComboBox<>();
        inferiorcomponentes = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tacomponentes = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        pnlComponentes = new javax.swing.JPanel();
        cbComponentes = new javax.swing.JComboBox<>();
        superior = new javax.swing.JPanel();
        superiologo = new javax.swing.JPanel();
        superlogosolo = new javax.swing.JPanel();
        llogo = new javax.swing.JLabel();
        superdatos = new javax.swing.JPanel();
        lnombre = new javax.swing.JLabel();
        ltel = new javax.swing.JLabel();
        ldir = new javax.swing.JLabel();
        infdatos = new javax.swing.JPanel();
        pnl1 = new javax.swing.JPanel();
        ltitulo = new javax.swing.JLabel();
        pnl2 = new javax.swing.JPanel();
        ldependencia = new javax.swing.JLabel();
        cbdependencia = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        tffecha = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfNota = new javax.swing.JTextField();
        pn3 = new javax.swing.JPanel();
        lpatrimonio = new javax.swing.JLabel();
        tfpatrimonio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbEstados = new javax.swing.JComboBox<>();
        inferior = new javax.swing.JPanel();
        iitecnico = new javax.swing.JLabel();
        tftecnico = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        centro.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        centro.setPreferredSize(new java.awt.Dimension(990, 342));
        centro.setLayout(new java.awt.GridLayout(2, 1));

        superiortareas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        superiortareas.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Se Realizó", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tatareas.setColumns(20);
        tatareas.setLineWrap(true);
        tatareas.setRows(5);
        jScrollPane2.setViewportView(tatareas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1556, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1536, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 85, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                    .addGap(13, 13, 13)))
        );

        superiortareas.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "TAREA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        pnlTareasDisponibles.setLayout(new java.awt.GridLayout(1, 1));

        cbtareas.setToolTipText("Tareas Realizadas");
        cbtareas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbtareasActionPerformed(evt);
            }
        });
        pnlTareasDisponibles.add(cbtareas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlTareasDisponibles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlTareasDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        superiortareas.add(jPanel2, java.awt.BorderLayout.NORTH);

        centro.add(superiortareas);

        inferiorcomponentes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        inferiorcomponentes.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Se Utilizó", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tacomponentes.setColumns(20);
        tacomponentes.setLineWrap(true);
        tacomponentes.setRows(5);
        jScrollPane1.setViewportView(tacomponentes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addGap(14, 14, 14)))
        );

        inferiorcomponentes.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "COMPONENTES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        pnlComponentes.setLayout(new java.awt.GridLayout(1, 1));

        cbComponentes.setToolTipText("Componentes Utilizados");
        cbComponentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbComponentesActionPerformed(evt);
            }
        });
        pnlComponentes.add(cbComponentes);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlComponentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        inferiorcomponentes.add(jPanel4, java.awt.BorderLayout.NORTH);

        centro.add(inferiorcomponentes);

        getContentPane().add(centro, java.awt.BorderLayout.CENTER);

        superior.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        superior.setLayout(new java.awt.GridLayout(2, 1));

        superlogosolo.setPreferredSize(new java.awt.Dimension(1011, 107));

        javax.swing.GroupLayout superlogosoloLayout = new javax.swing.GroupLayout(superlogosolo);
        superlogosolo.setLayout(superlogosoloLayout);
        superlogosoloLayout.setHorizontalGroup(
            superlogosoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1302, Short.MAX_VALUE)
            .addGroup(superlogosoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, superlogosoloLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(llogo, javax.swing.GroupLayout.PREFERRED_SIZE, 1282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        superlogosoloLayout.setVerticalGroup(
            superlogosoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 107, Short.MAX_VALUE)
            .addGroup(superlogosoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(llogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
        );

        superiologo.add(superlogosolo);

        superdatos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lnombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lnombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lnombre.setText("soporte@legislaturachaco.gov.ar");

        ltel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ltel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ltel.setText("- Tel: (0362) 4428149 -");

        ldir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ldir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ldir.setText("- José María Paz 170 - 1° Piso -");

        javax.swing.GroupLayout superdatosLayout = new javax.swing.GroupLayout(superdatos);
        superdatos.setLayout(superdatosLayout);
        superdatosLayout.setHorizontalGroup(
            superdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, superdatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(superdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ltel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ldir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lnombre, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addContainerGap())
        );
        superdatosLayout.setVerticalGroup(
            superdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(superdatosLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ldir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ltel)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        superiologo.add(superdatos);

        superior.add(superiologo);

        infdatos.setLayout(new java.awt.GridLayout(3, 1));

        ltitulo.setFont(new java.awt.Font("Antique Olive Compact", 1, 24)); // NOI18N
        ltitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ltitulo.setText("<<  SERVICIO TECNICO  >>");
        pnl1.add(ltitulo);

        infdatos.add(pnl1);

        ldependencia.setText("Dependencia:");
        pnl2.add(ldependencia);

        pnl2.add(cbdependencia);

        jLabel3.setText("Fecha:");
        pnl2.add(jLabel3);
        pnl2.add(tffecha);

        jLabel4.setText("Nº Nota:");
        pnl2.add(jLabel4);
        pnl2.add(tfNota);

        infdatos.add(pnl2);

        lpatrimonio.setText("Nº Patrimonio");
        pn3.add(lpatrimonio);
        pn3.add(tfpatrimonio);

        jLabel5.setText("Ponderación:");
        pn3.add(jLabel5);
        pn3.add(jTextField1);

        jLabel6.setText("Estado");
        pn3.add(jLabel6);

        pn3.add(cbEstados);

        infdatos.add(pn3);

        superior.add(infdatos);

        getContentPane().add(superior, java.awt.BorderLayout.PAGE_START);

        inferior.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inferior.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        iitecnico.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        iitecnico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iitecnico.setText("TECNICO :");
        inferior.add(iitecnico);
        inferior.add(tftecnico);

        getContentPane().add(inferior, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbtareasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbtareasActionPerformed
        this.tatareas.append((String) cbtareas.getSelectedItem() + ". ");
    }//GEN-LAST:event_cbtareasActionPerformed

    private void cbComponentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbComponentesActionPerformed
        this.tacomponentes.append((String) cbComponentes.getSelectedItem() + ". ");
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
                } catch (Exception ex) {
                    System.err.println("Error: " + ex.getLocalizedMessage());
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbComponentes;
    private javax.swing.JComboBox<String> cbEstados;
    private javax.swing.JComboBox<String> cbdependencia;
    private javax.swing.JComboBox<String> cbtareas;
    private javax.swing.JPanel centro;
    private javax.swing.JLabel iitecnico;
    private javax.swing.JPanel infdatos;
    private javax.swing.JPanel inferior;
    private javax.swing.JPanel inferiorcomponentes;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel ldependencia;
    private javax.swing.JLabel ldir;
    private javax.swing.JLabel llogo;
    private javax.swing.JLabel lnombre;
    private javax.swing.JLabel lpatrimonio;
    private javax.swing.JLabel ltel;
    private javax.swing.JLabel ltitulo;
    private javax.swing.JPanel pn3;
    private javax.swing.JPanel pnl1;
    private javax.swing.JPanel pnl2;
    private javax.swing.JPanel pnlComponentes;
    private javax.swing.JPanel pnlTareasDisponibles;
    private javax.swing.JPanel superdatos;
    private javax.swing.JPanel superiologo;
    private javax.swing.JPanel superior;
    private javax.swing.JPanel superiortareas;
    private javax.swing.JPanel superlogosolo;
    private javax.swing.JTextArea tacomponentes;
    private javax.swing.JTextArea tatareas;
    private javax.swing.JTextField tfNota;
    private javax.swing.JTextField tffecha;
    private javax.swing.JTextField tfpatrimonio;
    private javax.swing.JTextField tftecnico;
    // End of variables declaration//GEN-END:variables

}
