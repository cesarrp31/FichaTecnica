/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fichatecnica;

import auxiliar.GestorArchivo;
import auxiliar.configuraciones.ConfiguracionCorreo;
import auxiliar.configuraciones.ConfiguracionGeneral;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import ventanas.FichaTecnicaImpresion;

/**
 *
 * @author coperalta
 */
public class FichaTecnica {

    public static String NOMBRE_APP = "SRTI Sistema de Registro de Tareas Informáticas 2016 v2.0";
    
    public static ConfiguracionGeneral CONFIG_GENERAL;
    public static ConfiguracionCorreo CONFIG_CORREO;
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
            //ft.setLocationRelativeTo(null);
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
    }
}
