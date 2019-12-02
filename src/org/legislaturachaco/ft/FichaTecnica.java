/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.ft;

import org.legislaturachaco.com.gral.GestorArchivo;
import org.legislaturachaco.com.gral.conf.ConfiguracionCorreo;
import org.legislaturachaco.com.gral.conf.ConfiguracionGeneral;
import org.legislaturachaco.com.gral.conf.ConfiguracionTecnico;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import org.legislaturachaco.com.gral.GestorEntornoEjecucion;
import org.legislaturachaco.com.gral.PlataformaException;
import org.legislaturachaco.ft.ventanas.FichaTecnicaImpresion;
import org.legislaturachaco.com.gral.SOException;
import org.legislaturachaco.pr.Carga;

/**
 *
 * @author coperalta
 */
public class FichaTecnica {
    
    public static ConfiguracionGeneral CONFIG_GENERAL;
    public static ConfiguracionCorreo CONFIG_CORREO;
    public static ConfiguracionTecnico CONFIG_TECNICO;
    
    private static String tipoDLL= null, nombreOS= null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        
        try{
            nombreOS= getSO();
        }catch(Exception ex){
            System.err.println("Error: " + ex.getLocalizedMessage());
            ex.printStackTrace();
            return;
        }
        
        try{
            tipoDLL= GestorEntornoEjecucion.getArcComputadora();
        }catch(Exception ex){
            System.err.println("Error: " + ex.getLocalizedMessage());
            ex.printStackTrace();
            return;
        }

        try {
            cargarArchivosConfiguraciones();
        } catch (Exception e) {
            System.err.println("No se puede cargar el archivo de configuracion inicial! " 
                    + e.getLocalizedMessage());
            e.printStackTrace();
            return;
        }

        System.out.println("OS: " + nombreOS + ". ARQ: " + tipoDLL);
        System.out.println("Default Encoding: "+System.getProperty("file.encoding"));        
        try {
            /*if(args.length>0 && args[0].equals("carga"))            
                iniciarCarga();
            else*/
                iniciarFichaTecnica();
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getLocalizedMessage());
            ex.printStackTrace();
            return;
        }
    }
    
    private static void iniciarFichaTecnica() throws IOException{
        FichaTecnicaImpresion ft = new FichaTecnicaImpresion(tipoDLL);
        ft.pack();
        ft.setLocationRelativeTo(null);
        ft.setExtendedState(ft.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        ft.setVisible(true);
    }
    
    private static void iniciarCarga() throws IOException{
        //JFrame v= new JFrame();
        Carga c= new Carga();
        c.setLocationRelativeTo(null);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //c.setExtendedState(v.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        c.pack();
        c.setVisible(true);
    }

    protected static void cargarArchivosConfiguraciones() throws IOException {
        CONFIG_GENERAL = new ConfiguracionGeneral();
        System.out.println("Cargado correctamente el archivo de configuración inicial!");

        String crpArchConfig = CONFIG_GENERAL.getCarpetaConfiguracionCorreo() + GestorArchivo.SEPARADOR,
               nomArchConfig = crpArchConfig + CONFIG_GENERAL.getArchivoConfiguracionCorreo();
        System.out.println("Archivo de Correo a buscar: "+nomArchConfig);
        CONFIG_CORREO = new ConfiguracionCorreo(nomArchConfig);
        System.out.println("Cargado correctamente el archivo de configuración de correo!");
        
        crpArchConfig = CONFIG_GENERAL.getCarpetaConfiguracionTecnico() + GestorArchivo.SEPARADOR;
        nomArchConfig = crpArchConfig + CONFIG_GENERAL.getArchivoConfiguracionTecnico();
        System.out.println("Archivo de Tecnico a buscar: "+nomArchConfig);
        CONFIG_TECNICO = new ConfiguracionTecnico(nomArchConfig);
        System.out.println("Cargado correctamente el archivo de configuración del técnico!: "
                + CONFIG_TECNICO.getNombreTecnico());
    }
    
    protected static String tipoArqDLL(){
        String os= GestorEntornoEjecucion.getSOComputadora(),
                arq= GestorEntornoEjecucion.getArcComputadora();
        
        if(os.startsWith(GestorEntornoEjecucion.WINDOWS)&&
           arq.endsWith(GestorEntornoEjecucion.ARQ_32_BIT)) return "32";
        
        if(os.startsWith(GestorEntornoEjecucion.WINDOWS)&&
           arq.endsWith(GestorEntornoEjecucion.ARQ_64_BIT)) return "64";
        
        if(os.startsWith(GestorEntornoEjecucion.LINUX)&&
           arq.endsWith(GestorEntornoEjecucion.ARQ_32_BIT)) return "32";
        
        if(os.startsWith(GestorEntornoEjecucion.LINUX)&&
           arq.endsWith(GestorEntornoEjecucion.ARQ_64_BIT)) return "64";
        
        throw new PlataformaException();
    }
    
    private static String getSO(){
        String os= GestorEntornoEjecucion.getSOComputadora();
        
        if((!os.startsWith(GestorEntornoEjecucion.WINDOWS))&&
                (!os.startsWith(GestorEntornoEjecucion.LINUX))){
            throw new SOException();
        }        
        return os;
    }
}
