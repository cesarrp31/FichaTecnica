/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import auxiliar.CodigoQR;
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
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import reporte.Reporte;
import auxiliar.IGestionArchivo;
import auxiliar.IGestorLectorArchivoTexto;
import auxiliar.LimiteCaracteresDocument;
import auxiliar.configuraciones.ConfiguracionGeneral;
import static fichatecnica.FichaTecnica.CONFIG_GENERAL;
import static fichatecnica.FichaTecnica.CONFIG_TECNICO;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 *
 * @author jsilva
 */
public class FichaTecnicaImpresion extends javax.swing.JFrame implements IGestorLectorArchivoTexto{

    private Mail vtnCorreo;
    private List<String> lstTareas, lstComponentes, lstDependencias, 
                         lstTemp, lstEstados, lstPonderaciones;
    private final String crpImg = CONFIG_GENERAL.getCarpetaImagenes() + GestorArchivo.SEPARADOR,
            crpRec = CONFIG_GENERAL.getCarpetaRecursos() + GestorArchivo.SEPARADOR;
    
    private final GestorArchivoFichaTecnica iga;
    
    private String ingresoSeleccionado;
    
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
        cargarValoresComboBox();
        inicializarPantallaCarga();
        inicializarValoresEstaticos();
        formatearPantalla();
        inicializarBarraHerramientas();
        
        this.iga= new GestorArchivoFichaTecnica(this);
        this.tacomponentes.setWrapStyleWord(true);
        this.tatareas.setWrapStyleWord(true);
        Reporte.prepararReporte();
        
        //No se utiliza por ahora ambos paneles
        pnDescComp.setVisible(false);
        pnDescTarea.setVisible(false);
    }

    private void inicializarComponentes() {
        //inicializarComboBox(cbComponentes);
        inicializarComboBox(cbdependencia);
        inicializarComboBox(cbtareas);
    }

    private void inicializarMenu() throws FileNotFoundException {
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
                //configuracion= new JMenuItem("Configuracion"),
                configTecnico= new JMenuItem("Configuracion de Técnico"),
                acercaDe = new JMenuItem("Acerca de...");

        nuevo.setAccelerator(KeyStroke.getKeyStroke(
                             KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        abrir.setAccelerator(KeyStroke.getKeyStroke(
                             KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        guardar.setAccelerator(KeyStroke.getKeyStroke(
                             KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        imprimir.setAccelerator(KeyStroke.getKeyStroke(
                             KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        enviar.setAccelerator(KeyStroke.getKeyStroke(
                             KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        salir.setAccelerator(KeyStroke.getKeyStroke(
                             KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        acercaDe.setAccelerator(KeyStroke.getKeyStroke(
                             KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        configTecnico.setAccelerator(KeyStroke.getKeyStroke(
                             KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        
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
                vntAcercaDe();
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
        configuraciones.add(configuracion);
        */
        
        configTecnico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vntConfigTecnico();
            }
        });
        configuraciones.add(configTecnico);
        
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

        String e = crpImg + CONFIG_GENERAL.getImagenBotonEnviar24(),
                i = crpImg + CONFIG_GENERAL.getImagenBotonImprimir24(),
                n = crpImg + CONFIG_GENERAL.getImagenBotonNuevo24(),
                g = crpImg + CONFIG_GENERAL.getImagenBotonGuardar24(),
                a = crpImg + CONFIG_GENERAL.getImagenBotonAbrir24(),
                s = crpImg + CONFIG_GENERAL.getImagenBotonSalir24();
        
        ImageIcon ii = GestorArchivo.crearImageIcon(e, "");
        enviar.setIcon(ii);
        
        ii = GestorArchivo.crearImageIcon(i, "");
        imprimir.setIcon(ii);

        ii = GestorArchivo.crearImageIcon(n, "");
        nuevo.setIcon(ii);

        ii = GestorArchivo.crearImageIcon(a, "");
        abrir.setIcon(ii);

        ii = GestorArchivo.crearImageIcon(g, "");
        guardar.setIcon(ii);
        
        ii = GestorArchivo.crearImageIcon(s, "");
        salir.setIcon(ii);
        
        this.setJMenuBar(mb);
    }

    private void vntAcercaDe(){
        JDialog v= new JDialog(this, true);
        v.setTitle(this.getTitle());
        v.setContentPane(new PnlAcercaDe());
        v.pack();
        v.setLocationRelativeTo(null);
        v.setVisible(true);
    }
    
    private void inicializarBarraHerramientas() throws FileNotFoundException {
        JToolBar barraHerramientas = new JToolBar();
        String e = crpImg + CONFIG_GENERAL.getImagenBotonEnviar(),
                i = crpImg + CONFIG_GENERAL.getImagenBotonImprimir(),
                n = crpImg + CONFIG_GENERAL.getImagenBotonNuevo(),
                g = crpImg + CONFIG_GENERAL.getImagenBotonGuardar(),
                a = crpImg + CONFIG_GENERAL.getImagenBotonAbrir();

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
        barraHerramientas.addSeparator();
        barraHerramientas.add(Box.createHorizontalGlue());
        
        String logo = CONFIG_GENERAL.getCarpetaImagenes() + GestorArchivo.SEPARADOR + CONFIG_GENERAL.getImagenLogoBarra();
        JLabel icono= new JLabel();
        icono.setIcon(GestorArchivo.crearImageIcon(logo, ""));
        barraHerramientas.add(icono);
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
        String logo = crpImg + CONFIG_GENERAL.getImagenLogoApp(),
                ic = crpImg + CONFIG_GENERAL.getImagenIconoApp();
        /*
        llogo.setIcon(GestorArchivo.crearImageIcon(logo, ""));
        superlogosolo.add(llogo);*/

        setIconImage(GestorArchivo.crearImageIcon(ic, "").getImage());

        this.tffecha.setEditable(false);
        int maxChars;
        try {
            maxChars = Integer.valueOf(CONFIG_GENERAL.getConfiguracionCantidadCaracteresTextField());
        } catch (NumberFormatException e) {
            System.err.println(e.getLocalizedMessage());
            maxChars = 250;
        }
        this.setTitle(ConfiguracionGeneral.APLICACION_NOMBRE+" "+ConfiguracionGeneral.APLICACION_VERSION);
        //Limite de cantidad de caracteres
        tatareas.setDocument(new LimiteCaracteresDocument(maxChars));
        tacomponentes.setDocument(new LimiteCaracteresDocument(maxChars));
        /*AbstractDocument pDoc = (AbstractDocument) tatareas.getDocument();
        pDoc.setDocumentFilter(new DocumentSizeFilter(maxChars));
        
        pDoc = (AbstractDocument) tacomponentes.getDocument();
        pDoc.setDocumentFilter(new DocumentSizeFilter(maxChars));*/
        ButtonGroup group = new ButtonGroup();
        group.add(rbIngCorreo);
        group.add(rbIngTel);
        group.add(rbIngNota);
        group.add(rbIngMos);
        
        agregarActionListenerRadioButton(rbIngCorreo);
        agregarActionListenerRadioButton(rbIngTel);
        agregarActionListenerRadioButton(rbIngNota);
        agregarActionListenerRadioButton(rbIngMos);
    }

    private void inicializarValoresDesdeArchivo() throws FileNotFoundException, IOException {        
        lstTareas = new ArrayList<>();
        lstComponentes = new ArrayList<>();
        lstDependencias = new ArrayList<>();
        lstEstados = new ArrayList<>();
        lstPonderaciones= new ArrayList<>();

        cargarLista(lstTareas, CONFIG_GENERAL.getNombreArchivoTareas());
        cargarLista(lstComponentes, CONFIG_GENERAL.getNombreArchivoComponentes());
        cargarLista(lstDependencias, CONFIG_GENERAL.getNombreArchivoDependencias());
        cargarLista(lstEstados, CONFIG_GENERAL.getNombreArchivoEstados());
        cargarLista(lstPonderaciones, CONFIG_GENERAL.getNombreArchivoPonderaciones());
    }
    
    private void cargarLista(List<String> lstDatos, String archivoDatos) throws FileNotFoundException, IOException {
        lstTemp= lstDatos;
        GestorArchivo.obtenerPropiedades(crpRec + archivoDatos, this);
    }

    private void inicializarPantallaCarga() {
        //cbComponentes.setSelectedItem(null);
        cbtareas.setSelectedItem(null);
        cbdependencia.setSelectedItem(null);

        this.tfpatrimonio.setText("");
        this.tatareas.setText("");
        this.tacomponentes.setText("");
        this.tfNota.setText("");
        this.cbEstados.setSelectedIndex(0);
        this.cbponderacion.setSelectedIndex(0);
        Date actual = new Date();
        tffecha.setText(new SimpleDateFormat(CONFIG_GENERAL.getConfiguracionFormatoFecha()).format(actual));

        boolean cargarUsuarioSesion= Boolean.valueOf(CONFIG_GENERAL.getDefaultCargarNombreUsuarioSesion());
        if(cargarUsuarioSesion)
            actualizarNombreTecnico(System.getProperty("user.name"));
        else
            actualizarNombreTecnico(CONFIG_TECNICO.getNombreTecnico());
        
        rbIngCorreo.setSelected(true);
        this.ingresoSeleccionado= rbIngCorreo.getText();
        
        this.tfUsuario.setText("");
        this.tfClave.setText("");
    }
    
    private void agregarActionListenerRadioButton(JRadioButton rb){
        rb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton r= (JRadioButton) e.getSource();
                setIngresoSeleccionado(r.getText());
            }
        });
    }

    private void formatearPantalla(){
        List<JComponent> componentes= new ArrayList<>();
        componentes.add(ldependencia);
        componentes.add(cbdependencia);
        componentes.add(lfecha);
        componentes.add(tffecha);
        componentes.add(lnota);
        componentes.add(tfNota);
        crearGrupo(pnl2, componentes);
        
        componentes= new ArrayList<>();
        componentes.add(lpatrimonio);
        componentes.add(tfpatrimonio);
        componentes.add(lponderacion);
        componentes.add(cbponderacion);
        componentes.add(lestado);
        componentes.add(cbEstados);
        crearGrupo(pnl3, componentes);
        
        componentes= new ArrayList<>();
        componentes.add(ltecnico);
        componentes.add(tftecnico);
        crearGrupo(datosTecnico, componentes);
                
        componentes= new ArrayList<>();
        componentes.add(lUsuario);
        componentes.add(tfUsuario);
        componentes.add(lClave);
        componentes.add(tfClave);
        crearGrupo(datosUsuario, componentes);
        
    }
    
    private void crearGrupo(JPanel contenedor, List<JComponent> componentes){
        GroupLayout layout = new GroupLayout(contenedor);
        contenedor.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        GroupLayout.SequentialGroup hGroup = crearGrupoH(layout, componentes);
        layout.setHorizontalGroup(hGroup);
        GroupLayout.SequentialGroup vGroup = crearGrupoV(layout, componentes, GroupLayout.Alignment.BASELINE);
        layout.setVerticalGroup(vGroup);
        
    }
    
    private GroupLayout.SequentialGroup crearGrupoH(GroupLayout layout, List<JComponent> componentes){
        GroupLayout.SequentialGroup grupoSecuencial= layout.createSequentialGroup();

        for(JComponent componente: componentes){
            grupoSecuencial.addComponent(componente);
        }
        return grupoSecuencial;
    }
    
    private GroupLayout.SequentialGroup crearGrupoV(GroupLayout layout, List<JComponent> componentes, 
            GroupLayout.Alignment alineacion){
        GroupLayout.SequentialGroup grupoSecuencial= layout.createSequentialGroup();
        GroupLayout.ParallelGroup grupoParalelo;
        if(alineacion == null) grupoParalelo= layout.createParallelGroup();
        else grupoParalelo= layout.createParallelGroup(alineacion);
        
        for(JComponent componente: componentes){
            grupoParalelo.addComponent(componente);
        }
        
        grupoSecuencial.addGroup(grupoParalelo);
        return grupoSecuencial;
    }
    
    @Override
    public void agregarLinea(String linea) {
        lstTemp.add(linea);
    }

    private void cargarValoresComboBox() {
        cargarValores(cbtareas, lstTareas);
        //cargarValores(cbComponentes, lstComponentes);
        cargarValores(cbdependencia, lstDependencias);
        
        cbEstados.setModel(new DefaultComboBoxModel<String>(new Vector(lstEstados)));
        cbponderacion.setModel(new DefaultComboBoxModel<String>(new Vector(lstPonderaciones)));
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
        String pathCompleto = CONFIG_GENERAL.getCarpetaTemporal()
                + GestorArchivo.SEPARADOR
                + CONFIG_GENERAL.getImagenCodigoQR();
             
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
                vtnCorreo.asunto(tfpatrimonio.getText(), tfNota.getText());
                vtnCorreo.datos(this.getDatosFichaTecnica());
                vtnCorreo.pack();
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
    
    protected DatosFichaTecnica getDatosFichaTecnica() {
        String dep = cbdependencia.getSelectedItem()==null?"":cbdependencia.getSelectedItem().toString();
        DatosFichaTecnica dft = new DatosFichaTecnica(
                    quitarCaracteresEspeciales(dep), 
                    quitarCaracteresEspeciales(tffecha.getText()), 
                    quitarCaracteresEspeciales(tfNota.getText()), 
                    quitarCaracteresEspeciales(tfpatrimonio.getText()), 
                    quitarCaracteresEspeciales(cbponderacion.getSelectedItem().toString()), 
                    quitarCaracteresEspeciales(cbEstados.getSelectedItem().toString()),
                    quitarCaracteresEspeciales(tatareas.getText()), 
                    quitarCaracteresEspeciales(tacomponentes.getText()), 
                    quitarCaracteresEspeciales(tftecnico.getText()),
                    quitarCaracteresEspeciales(tfUsuario.getText()), 
                    quitarCaracteresEspeciales(tfClave.getText()), 
                    quitarCaracteresEspeciales(ingresoSeleccionado));
        return dft;
    }
    
    private String quitarCaracteresEspeciales(String texto){
        texto= texto.replaceAll("\n", " ");
        texto= texto.replaceAll("\t", " ");
        return texto.trim();
    }

    private void  abrir(){
        GestorArchivo.abrirArchivo(this.getGestorArchivo());
    }
    
    private void guardar(){
        GestorArchivo.guardar(this.getGestorArchivo());
    }
    
    private void vntConfigTecnico(){
        ConfiguracionTecnico c = new ConfiguracionTecnico(this, true);
        c.setLocationRelativeTo(null);
        c.pack();
        c.setVisible(true);
    }
    
    private IGestionArchivo getGestorArchivo(){
        return iga;
    }

    protected JComboBox<String> getCBEstados() {
        return cbEstados;
    }

    protected JComboBox<String> getCBDependencia() {
        return cbdependencia;
    }

    protected JComboBox<String> getCBPonderacion() {
        return cbponderacion;
    }

    protected JTextArea getTAComponentes() {
        return tacomponentes;
    }

    protected JTextArea getTATareas() {
        return tatareas;
    }

    protected JTextField getTFNota() {
        return tfNota;
    }

    protected JTextField getTFFecha() {
        return tffecha;
    }

    protected JTextField getTFPatrimonio() {
        return tfpatrimonio;
    }

    protected JTextField getTFTecnico() {
        return tftecnico;
    }

    public JTextField getTfClave() {
        return tfClave;
    }

    public JTextField getTfUsuario() {
        return tfUsuario;
    }
    
    protected void seleccionarIngreso(String ingreso){
        if(rbIngMos.getText().equals(ingreso)){
            rbIngMos.setSelected(true);
            return;
        }
        if(rbIngNota.getText().equals(ingreso)){
            rbIngNota.setSelected(true);
            return;
        }
        if(rbIngTel.getText().equals(ingreso)){
            rbIngTel.setSelected(true);
            return;
        }
        rbIngCorreo.setSelected(true);
    }

    public String getIngresoSeleccionado() {
        return ingresoSeleccionado;
    }

    private void setIngresoSeleccionado(String ingresoSeleccionado) {
        this.ingresoSeleccionado = ingresoSeleccionado;
    }
    
    protected void actualizarNombreTecnico(String nombreTecnico){
        this.tftecnico.setText(nombreTecnico);
    }
    
    /*
    private void crearVentanaConfiguraciones() {
        Configuraciones c = new Configuraciones(this, true);
        c.setLocationRelativeTo(null);
        c.pack();
        c.setVisible(true);
    }
    */
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
        pnDescTarea = new javax.swing.JPanel();
        pnlTareasDisponibles = new javax.swing.JPanel();
        cbtareas = new javax.swing.JComboBox<>();
        inferiorcomponentes = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tacomponentes = new javax.swing.JTextArea();
        pnDescComp = new javax.swing.JPanel();
        pnlComponentes = new javax.swing.JPanel();
        cbComponentes = new javax.swing.JComboBox<>();
        superior = new javax.swing.JPanel();
        pnl2 = new javax.swing.JPanel();
        ldependencia = new javax.swing.JLabel();
        cbdependencia = new javax.swing.JComboBox<>();
        lfecha = new javax.swing.JLabel();
        tffecha = new javax.swing.JTextField();
        lnota = new javax.swing.JLabel();
        tfNota = new javax.swing.JTextField();
        pnl3 = new javax.swing.JPanel();
        lpatrimonio = new javax.swing.JLabel();
        tfpatrimonio = new javax.swing.JTextField();
        lponderacion = new javax.swing.JLabel();
        cbponderacion = new javax.swing.JComboBox<>();
        lestado = new javax.swing.JLabel();
        cbEstados = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        rbIngCorreo = new javax.swing.JRadioButton();
        rbIngTel = new javax.swing.JRadioButton();
        rbIngNota = new javax.swing.JRadioButton();
        rbIngMos = new javax.swing.JRadioButton();
        inferior = new javax.swing.JPanel();
        datosTecnico = new javax.swing.JPanel();
        ltecnico = new javax.swing.JLabel();
        tftecnico = new javax.swing.JTextField();
        datosUsuario = new javax.swing.JPanel();
        lUsuario = new javax.swing.JLabel();
        tfUsuario = new javax.swing.JTextField();
        lClave = new javax.swing.JLabel();
        tfClave = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        centro.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        centro.setPreferredSize(new java.awt.Dimension(990, 342));
        centro.setLayout(new java.awt.GridLayout(2, 1));

        superiortareas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        superiortareas.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Se Realizó", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tatareas.setColumns(20);
        tatareas.setLineWrap(true);
        tatareas.setRows(5);
        tatareas.setToolTipText("Ingrese las Tarea/s realizada/s");
        jScrollPane2.setViewportView(tatareas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1566, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1546, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addGap(13, 13, 13)))
        );

        superiortareas.add(jPanel1, java.awt.BorderLayout.CENTER);

        pnDescTarea.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "TAREA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        pnlTareasDisponibles.setLayout(new java.awt.GridLayout(1, 1));

        cbtareas.setToolTipText("Tareas Realizadas");
        cbtareas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbtareasActionPerformed(evt);
            }
        });
        pnlTareasDisponibles.add(cbtareas);

        javax.swing.GroupLayout pnDescTareaLayout = new javax.swing.GroupLayout(pnDescTarea);
        pnDescTarea.setLayout(pnDescTareaLayout);
        pnDescTareaLayout.setHorizontalGroup(
            pnDescTareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(pnDescTareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnDescTareaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlTareasDisponibles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnDescTareaLayout.setVerticalGroup(
            pnDescTareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
            .addGroup(pnDescTareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnDescTareaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlTareasDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        superiortareas.add(pnDescTarea, java.awt.BorderLayout.NORTH);

        centro.add(superiortareas);

        inferiorcomponentes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        inferiorcomponentes.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Se Utilizó", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tacomponentes.setColumns(20);
        tacomponentes.setLineWrap(true);
        tacomponentes.setRows(5);
        tacomponentes.setToolTipText("Ingrese los componentes utilizados");
        jScrollPane1.setViewportView(tacomponentes);
        tacomponentes.getAccessibleContext().setAccessibleDescription("Componentes Utilizados");

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
            .addGap(0, 160, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addGap(14, 14, 14)))
        );

        inferiorcomponentes.add(jPanel3, java.awt.BorderLayout.CENTER);

        pnDescComp.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "COMPONENTES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        pnlComponentes.setLayout(new java.awt.GridLayout(1, 1));

        cbComponentes.setToolTipText("Componentes Utilizados");
        cbComponentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbComponentesActionPerformed(evt);
            }
        });
        pnlComponentes.add(cbComponentes);

        javax.swing.GroupLayout pnDescCompLayout = new javax.swing.GroupLayout(pnDescComp);
        pnDescComp.setLayout(pnDescCompLayout);
        pnDescCompLayout.setHorizontalGroup(
            pnDescCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(pnDescCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnDescCompLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlComponentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnDescCompLayout.setVerticalGroup(
            pnDescCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
            .addGroup(pnDescCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnDescCompLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        inferiorcomponentes.add(pnDescComp, java.awt.BorderLayout.NORTH);

        centro.add(inferiorcomponentes);

        getContentPane().add(centro, java.awt.BorderLayout.CENTER);

        superior.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        superior.setLayout(new javax.swing.BoxLayout(superior, javax.swing.BoxLayout.PAGE_AXIS));

        ldependencia.setText("Dependencia:");
        pnl2.add(ldependencia);

        cbdependencia.setToolTipText("Seleccione una Dependencia");
        pnl2.add(cbdependencia);

        lfecha.setText("Fecha:");
        pnl2.add(lfecha);

        tffecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tffecha.setMaximumSize(new java.awt.Dimension(150, 20));
        tffecha.setMinimumSize(new java.awt.Dimension(150, 20));
        tffecha.setPreferredSize(new java.awt.Dimension(150, 20));
        pnl2.add(tffecha);

        lnota.setText("A/S");
        pnl2.add(lnota);

        tfNota.setToolTipText("Ingrese un nro. de Actuación Simple");
        tfNota.setMaximumSize(new java.awt.Dimension(100, 20));
        tfNota.setMinimumSize(new java.awt.Dimension(100, 20));
        tfNota.setPreferredSize(new java.awt.Dimension(100, 20));
        pnl2.add(tfNota);

        superior.add(pnl2);

        lpatrimonio.setText("Nº Patrimonio");
        pnl3.add(lpatrimonio);

        tfpatrimonio.setToolTipText("Ingrese uno o varios nros. de patrimonios");
        pnl3.add(tfpatrimonio);

        lponderacion.setText("Ponderación:");
        pnl3.add(lponderacion);

        cbponderacion.setToolTipText("Seleccione una ponderación");
        cbponderacion.setMaximumSize(new java.awt.Dimension(52, 22));
        cbponderacion.setMinimumSize(new java.awt.Dimension(52, 22));
        cbponderacion.setPreferredSize(new java.awt.Dimension(52, 22));
        pnl3.add(cbponderacion);

        lestado.setText("Estado");
        pnl3.add(lestado);

        cbEstados.setToolTipText("Seleccione un Estado del Servicio Técnico");
        cbEstados.setMaximumSize(new java.awt.Dimension(200, 22));
        cbEstados.setMinimumSize(new java.awt.Dimension(200, 22));
        cbEstados.setPreferredSize(new java.awt.Dimension(200, 22));
        pnl3.add(cbEstados);

        superior.add(pnl3);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Solicitado por:"));

        rbIngCorreo.setText("Correo Electrónico");
        rbIngCorreo.setToolTipText("Seleccione una opción");
        jPanel2.add(rbIngCorreo);

        rbIngTel.setText("Teléfono");
        rbIngTel.setToolTipText("Seleccione una opción");
        jPanel2.add(rbIngTel);

        rbIngNota.setText("Nota");
        rbIngNota.setToolTipText("Seleccione una opción");
        jPanel2.add(rbIngNota);

        rbIngMos.setText("Mostrador");
        rbIngMos.setToolTipText("Seleccione una opción");
        jPanel2.add(rbIngMos);

        superior.add(jPanel2);

        getContentPane().add(superior, java.awt.BorderLayout.NORTH);

        inferior.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inferior.setLayout(new java.awt.BorderLayout());

        datosTecnico.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        ltecnico.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ltecnico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ltecnico.setText("TECNICO:");
        datosTecnico.add(ltecnico);

        tftecnico.setToolTipText("Ingrese nombre del técnico que realizó el servicio técnico");
        datosTecnico.add(tftecnico);

        inferior.add(datosTecnico, java.awt.BorderLayout.NORTH);

        datosUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Usuario"));
        datosUsuario.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lUsuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lUsuario.setText("Usuario");
        datosUsuario.add(lUsuario);

        tfUsuario.setToolTipText("Ingrese el nombre de usuario que se cargo por última vez en la pc");
        datosUsuario.add(tfUsuario);

        lClave.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lClave.setText("Contraseña");
        datosUsuario.add(lClave);

        tfClave.setToolTipText("Ingrese la contraseña de usuario que se cargo por última vez en la pc");
        datosUsuario.add(tfClave);

        inferior.add(datosUsuario, java.awt.BorderLayout.SOUTH);

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
    private javax.swing.JComboBox<String> cbponderacion;
    private javax.swing.JComboBox<String> cbtareas;
    private javax.swing.JPanel centro;
    private javax.swing.JPanel datosTecnico;
    private javax.swing.JPanel datosUsuario;
    private javax.swing.JPanel inferior;
    private javax.swing.JPanel inferiorcomponentes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lClave;
    private javax.swing.JLabel lUsuario;
    private javax.swing.JLabel ldependencia;
    private javax.swing.JLabel lestado;
    private javax.swing.JLabel lfecha;
    private javax.swing.JLabel lnota;
    private javax.swing.JLabel lpatrimonio;
    private javax.swing.JLabel lponderacion;
    private javax.swing.JLabel ltecnico;
    private javax.swing.JPanel pnDescComp;
    private javax.swing.JPanel pnDescTarea;
    private javax.swing.JPanel pnl2;
    private javax.swing.JPanel pnl3;
    private javax.swing.JPanel pnlComponentes;
    private javax.swing.JPanel pnlTareasDisponibles;
    private javax.swing.JRadioButton rbIngCorreo;
    private javax.swing.JRadioButton rbIngMos;
    private javax.swing.JRadioButton rbIngNota;
    private javax.swing.JRadioButton rbIngTel;
    private javax.swing.JPanel superior;
    private javax.swing.JPanel superiortareas;
    private javax.swing.JTextArea tacomponentes;
    private javax.swing.JTextArea tatareas;
    private javax.swing.JTextField tfClave;
    private javax.swing.JTextField tfNota;
    private javax.swing.JTextField tfUsuario;
    private javax.swing.JTextField tffecha;
    private javax.swing.JTextField tfpatrimonio;
    private javax.swing.JTextField tftecnico;
    // End of variables declaration//GEN-END:variables

}

class GestorArchivoFichaTecnica implements IGestionArchivo{
    private final FichaTecnicaImpresion fti;

    public GestorArchivoFichaTecnica(FichaTecnicaImpresion fti) {
        this.fti = fti;
    }

    @Override
    public void abrir(File fichero) {
        //System.out.println("Abrir: " + fichero);
        try {
            StringBuilder sb= new StringBuilder();
            String aux="";
            String[] aux2;
            Scanner scnr = new Scanner(fichero);
            if (scnr.hasNextLine()) {
                do{
                    sb.append(scnr.nextLine());
                }while(scnr.hasNextLine());
                aux= sb.toString();
                System.out.println("Valor leido: "+aux);
                aux2 = aux.split(DatosFichaTecnica.DELIMITADOR);
                fti.getCBDependencia().setSelectedItem(formatearCampoLeido(aux2[0]));
                fti.getTFFecha().setText(formatearCampoLeido(aux2[1]));
                fti.getTFNota().setText(formatearCampoLeido(aux2[2]));
                fti.getTFPatrimonio().setText(formatearCampoLeido(aux2[3]));
                fti.getCBPonderacion().setSelectedItem(formatearCampoLeido(aux2[4]));
                fti.getCBEstados().setSelectedItem(formatearCampoLeido(aux2[5]));
                fti.getTATareas().setText(formatearCampoLeido(aux2[6]));
                fti.getTAComponentes().setText(formatearCampoLeido(aux2[7]));
                fti.getTFTecnico().setText(formatearCampoLeido(aux2[8]));
                fti.getTfUsuario().setText(formatearCampoLeido(aux2[9]));
                fti.getTfClave().setText(formatearCampoLeido(aux2[10]));
                fti.seleccionarIngreso(formatearCampoLeido(aux2[11]));
            }
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            e.printStackTrace();
        }        
    }
    
    private String formatearCampoLeido(String valor){
        String aux="";
        if (valor.startsWith(DatosFichaTecnica.SEPARADOR)){
            aux= valor.substring(1);
        }
        if (valor.endsWith(DatosFichaTecnica.SEPARADOR)){
            aux= aux.substring(0, aux.length()-1);
        }
        return aux;
    }
    
    @Override
    public void guardar(File fichero) {        
        BufferedWriter bw;
        String ext = "." + this.extensionArchivo(), texto="", salto="\n";
        try {
            if (fichero.getPath().endsWith(ext)) {
                bw = new BufferedWriter(new FileWriter(fichero));
            } else {
                bw = new BufferedWriter(new FileWriter(fichero + ext));
            }
            texto= fti.getDatosFichaTecnica().toString();
            if(texto.contains(salto))
                texto= texto.replace(salto, " ");
            bw.write(texto);
            bw.close();
            System.out.println("Datos guardados: "+fti.getDatosFichaTecnica().toString());
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            e.printStackTrace();
        }        
    }
    
    @Override
    public Frame ventana(){
        return this.fti;
    }
    
    @Override
    public String extensionArchivo(){
        return CONFIG_GENERAL.getConfiguracionArchivoExtension();
    }
    
    @Override
    public String descripcionTipoArchivo(){
        return CONFIG_GENERAL.getConfiguracionArchivoDescripcion();
    }
    
    @Override
    public String carpetaGuardado(){
        return CONFIG_GENERAL.getCarpetaGuardado();
    }    
}