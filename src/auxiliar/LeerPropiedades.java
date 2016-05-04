/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Cesar
 */
public class LeerPropiedades {
    private LeerPropiedades(){}
    private static final String SEPARADOR="/";
    
    public static Properties Leer(String nombreArchivo) throws FileNotFoundException, IOException, NullPointerException{
        if((nombreArchivo==null)||(nombreArchivo.isEmpty())){
            throw new RuntimeException("Nombre de archivo de propiedades inválido!: ");
        }
        Properties propiedad = new Properties();
        File archivo= GestorArchivo.cargarArchivo(nombreArchivo, SEPARADOR);
        propiedad.load(new FileInputStream(archivo));
        return propiedad;   
    }
}
