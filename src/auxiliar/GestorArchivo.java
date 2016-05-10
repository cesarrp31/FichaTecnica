/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author coperalta
 */
public class GestorArchivo {

    public static final String PATH_RAIZ_APP = System.getProperty("user.dir"),
                                SEPARADOR = System.getProperty("file.separator");

    public static File cargarArchivo(String nombreArchivo) throws FileNotFoundException{
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
            throw new FileNotFoundException("No se pudede cargar el archivo: "+pathCompleto);
        }
    }
    
    public static ImageIcon crearImageIcon(String path, String description) throws FileNotFoundException{
        File img = cargarArchivo(path);
        return new ImageIcon(img.getPath(), description);
    }
    
    public static Image crearImage(String path) throws FileNotFoundException, IOException{
        File img = cargarArchivo(path);
        return ImageIO.read(img);
    }

    public static Properties obtenerPropiedades(String nombreArchivo) throws FileNotFoundException, IOException{
        Properties propiedad = new Properties();
        File archivo = cargarArchivo(nombreArchivo);
        InputStream inputStream= new FileInputStream(archivo);
        try {
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            try {
                propiedad.load(reader);
            } finally {
                reader.close();
            }
} finally {
   inputStream.close();
}
        return propiedad;
    }
}
