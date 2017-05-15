/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.com.ft;

import org.legislaturachaco.com.gral.GestorArchivo;
import org.legislaturachaco.com.gral.conf.ConfiguracionCorreo;
import org.legislaturachaco.com.gral.conf.ConfiguracionGeneral;
import org.legislaturachaco.com.gral.conf.ConfiguracionTecnico;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import org.legislaturachaco.com.gral.GestorEntornoEjecucion;
import org.legislaturachaco.com.gral.PlataformaException;
import org.legislaturachaco.com.ft.ventanas.FichaTecnicaImpresion;

/**
 *
 * @author coperalta
 */
public class FichaTecnica {
    
    public static ConfiguracionGeneral CONFIG_GENERAL;
    public static ConfiguracionCorreo CONFIG_CORREO;
    public static ConfiguracionTecnico CONFIG_TECNICO;
    
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
            tipoArqDLL();
        }catch(Exception ex){
            System.err.println("Error: " + ex.getLocalizedMessage());
            ex.printStackTrace();
            return;
        }

        try {
            cargarArchivosConfiguraciones();
        } catch (Exception e) {
            System.err.println("No se puede cargar el archivo de configuracion inicial! " + e.getLocalizedMessage());
            e.printStackTrace();
            return;
        }

        try {
            FichaTecnicaImpresion ft = new FichaTecnicaImpresion();
            ft.pack();
            ft.setLocationRelativeTo(null);
            ft.setExtendedState(ft.getExtendedState() | JFrame.MAXIMIZED_BOTH);
            ft.setVisible(true);
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

    protected static void cargarArchivosConfiguraciones() throws IOException {
        CONFIG_GENERAL = new ConfiguracionGeneral();
        System.out.println("Cargado correctamente el archivo de configuración inicial!");

        String crpCorreo = CONFIG_GENERAL.getCarpetaConfiguracionCorreo() + GestorArchivo.SEPARADOR,
                nomArc = crpCorreo + CONFIG_GENERAL.getArchivoConfiguracionCorreo();
        CONFIG_CORREO = new ConfiguracionCorreo(nomArc);
        System.out.println("Cargado correctamente el archivo de configuración de correo!");
        
        CONFIG_TECNICO = new ConfiguracionTecnico();
        System.out.println("Cargado correctamente el archivo de configuración del técnico!: "+CONFIG_TECNICO.getNombreTecnico());
    }
    
    protected static String tipoArqDLL(){
        String tipoDLL= "", 
                os= GestorEntornoEjecucion.getSOComputadora(),
                arq= GestorEntornoEjecucion.getArcComputadora();
        
        if(os.startsWith(GestorEntornoEjecucion.WINDOWS)){
            if(arq.endsWith(GestorEntornoEjecucion.ARQ_32_BIT)) tipoDLL="32";
            else tipoDLL= "64";
        }else{
            throw new PlataformaException();
        }        
        return tipoDLL;
    }
}
