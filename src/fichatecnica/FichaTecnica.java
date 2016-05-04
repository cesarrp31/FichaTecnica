/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fichatecnica;

import auxiliar.LeerPropiedades;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import ventanas.FichaTecnicaImpresion;

/**
 *
 * @author coperalta
 */
public class FichaTecnica {
    public static Properties NOMBRE_ARCHIVOS;
    public static String NOMBRE_APP="SRTI Sistema de Registro de Tareas Informáticas 2016 v1.0";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Set System L&F
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } 
        catch (UnsupportedLookAndFeelException e) {}
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
        
        try {
            NOMBRE_ARCHIVOS=LeerPropiedades.Leer("configuaricionInicial.config");
            System.out.println("Cargado correctamente el archivo de configuración inicial!");
        }catch(Exception e){
            System.err.println("No se puede cargar el archivo de configuracion inicial! "+e.getLocalizedMessage());
            e.printStackTrace();
            return;
        }
        try {
            FichaTecnicaImpresion ft=new FichaTecnicaImpresion();
            ft.pack();
            ft.setLocationRelativeTo(null);
            ft.setVisible(true);
        } catch (FileNotFoundException ex) {
            System.err.println("Error: "+ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }    
}
