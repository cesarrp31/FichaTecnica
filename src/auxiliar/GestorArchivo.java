/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 *
 * @author coperalta
 */
public class GestorArchivo {
    
    public static File cargarArchivo(String path, String separador) throws NullPointerException, FileNotFoundException{
        File file;
        if(!path.startsWith(separador)){
            path= separador+path;
            System.out.print("Nuevo Path: "+path);
        }
        System.out.println(". Cargando archivo "+path+"...");
        URL url = (new Properties()).getClass().getResource(path);
        //file = new File((new Properties()).getClass().getResource(path).getPath());
        file= new File(url.getPath());
        System.out.println(path+" cargado.");
        if (file.getAbsolutePath() != null) {
            return file;
        } else {
            throw new NullPointerException("No se encuentra el archivo: " + path);
        }
    }
}
