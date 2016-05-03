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
            throw new RuntimeException("Nombre de archivo de propiedades inválido!: "+nombreArchivo);
        }
        Properties propiedad = new Properties();
        File archivo= buscarArchivo(nombreArchivo);

        propiedad.load(new FileInputStream(archivo));

        return propiedad;   
    }
    
    protected static File buscarArchivo(String path) throws FileNotFoundException {
        if(!path.startsWith(SEPARADOR)){
            path= SEPARADOR+path;
        }
        File file = new File(new Properties().getClass().getResource(path).getPath());
        if (file.getAbsolutePath() != null) {
            return file;
        } else {
            throw new NullPointerException("No se encuentra el archivo: " + path);
        }
    }
    
    public static void test(){
        String nomArc= "srvCorreo.config";
        System.out.println(System.getProperty("java.class.path"));
        System.out.println((new File(nomArc)).exists());
        try {
            Properties propiedad = LeerPropiedades.Leer(nomArc);
            
            System.out.println(propiedad.getProperty("mail.smtp.host"));
            System.out.println(propiedad.getProperty("mail.smtp.starttls.enable"));
            System.out.println(propiedad.getProperty("mail.smtp.port"));
            System.out.println(propiedad.getProperty("mail.smtp.user"));
            System.out.println(propiedad.getProperty("mail.smtp.auth"));
        } catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        LeerPropiedades.test();
    }
}
