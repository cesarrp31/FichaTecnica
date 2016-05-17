/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fichatecnica;

import auxiliar.GestorArchivo;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.UIManager;
import ventanas.FichaTecnicaImpresion;

/**
 *
 * @author coperalta
 */
public class FichaTecnica {

    public static Properties CONFIG_GENERAL, CONFIG_CORREO;
    public static String NOMBRE_APP = "SRTI Sistema de Registro de Tareas Informáticas 2016 v2.0";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

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
        CONFIG_GENERAL = GestorArchivo.obtenerPropiedades("config\\configuracionInicial.config");
        System.out.println("Cargado correctamente el archivo de configuración inicial!");

        String crpCorreo = CONFIG_GENERAL.getProperty("crp.configCorreo") + GestorArchivo.SEPARADOR,
                nomArc = crpCorreo + CONFIG_GENERAL.getProperty("cnf.Correo");
        CONFIG_CORREO = GestorArchivo.obtenerPropiedades(nomArc);
        System.out.println("Cargado correctamente el archivo de configuración de correo!");
    }
}
