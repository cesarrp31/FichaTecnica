/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.com.gral;

import org.legislaturachaco.ft.FichaTecnica;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Properties;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author coperalta
 */
public class GestorArchivo {

    public static final String PATH_RAIZ_APP = GestorEntornoEjecucion.getCarpetaTrabajo(),
            SEPARADOR = GestorEntornoEjecucion.getSeparadorCarpetas();

    public static File cargarArchivo(String nombreArchivo) throws FileNotFoundException {
        if ((nombreArchivo == null) || (nombreArchivo.isEmpty())) {
            throw new RuntimeException("Nombre de archivo de propiedades inválido!: ");
        }
        File file;
        String pathCompleto = PATH_RAIZ_APP + SEPARADOR + nombreArchivo;
        System.out.println("Directorio Raiz App: " + PATH_RAIZ_APP);
        System.out.print("Cargando archivo: " + pathCompleto);
        file = new File(pathCompleto);
        if (file.exists()) {
            System.out.println(". Cargado EXITOSAMENTE!");
            return file;
        } else {
            System.out.println(". NO SE PUDO CARGAR!");
            throw new FileNotFoundException("No se pudede cargar el archivo: " + pathCompleto);
        }
    }

    public static ImageIcon crearImageIcon(String path, String description) throws FileNotFoundException {
        File img = cargarArchivo(path);
        return new ImageIcon(img.getPath(), description);
    }

    public static Image crearImage(String path) throws FileNotFoundException, IOException {
        File img = cargarArchivo(path);
        return ImageIO.read(img);
    }

    public static Properties obtenerPropiedades(String nombreArchivo, String codificacion) throws FileNotFoundException, IOException {
        Properties propiedad = new Properties();
        File archivo = cargarArchivo(nombreArchivo);
        try (InputStream inputStream = new FileInputStream(archivo); Reader reader = new InputStreamReader(inputStream, codificacion)) {
            propiedad.load(reader);
        }
        return propiedad;
    }
    
    public static Properties obtenerPropiedades(String nombreArchivo) throws FileNotFoundException, IOException {
        String codificacion= "UTF-8";
        if(FichaTecnica.CONFIG_GENERAL != null)
            codificacion= FichaTecnica.CONFIG_GENERAL.getConfiguracionCodificacion();
        return obtenerPropiedades(nombreArchivo, codificacion);
    }
    public static void obtenerPropiedades(String nombreArchivo, IGestorLectorArchivoTexto gc) throws FileNotFoundException, IOException {
        String linea;
        File archivo = cargarArchivo(nombreArchivo);
        Scanner scnr = new Scanner(archivo, FichaTecnica.CONFIG_GENERAL.getConfiguracionCodificacion());
        while (scnr.hasNextLine()) {
            linea = scnr.nextLine();
            gc.agregarLinea(linea);
        }
    }
    
    public static void guardarPropiedades(Properties propiedad, String nombreArchivo) throws FileNotFoundException, IOException{
        try (OutputStream out = new FileOutputStream(nombreArchivo)) {
            propiedad.store(out, null);
        }
    }
    
    public static void abrirArchivo(IGestionArchivo gestor){
        JFileChooser fc = getJFileChooser(gestor);
        int returnVal = fc.showOpenDialog(gestor.ventana());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File fichero = fc.getSelectedFile();
            System.out.println("Abrir: " + fichero);
            gestor.abrir(fichero);
        }
    }

    public static void guardar(IGestionArchivo gestor) {
        JFileChooser fc = getJFileChooser(gestor);
        int returnVal = fc.showSaveDialog(gestor.ventana());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File fichero = fc.getSelectedFile();
            System.out.println("Guardar: " + fichero);
            gestor.guardar(fichero);
        }
    }

    private static JFileChooser getJFileChooser(IGestionArchivo gestor) {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(gestor.carpetaGuardado()));
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                gestor.descripcionTipoArchivo(),
                gestor.extensionArchivo());
        fc.setFileFilter(filter);
        return fc;
    }
}
