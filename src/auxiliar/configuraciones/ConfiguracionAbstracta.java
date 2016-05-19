/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar.configuraciones;

import auxiliar.GestorArchivo;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author coperalta
 */
public abstract class ConfiguracionAbstracta {
    private final Properties configuracion;

    protected ConfiguracionAbstracta(String ubicacion) throws IOException {
        configuracion = GestorArchivo.obtenerPropiedades(ubicacion);
    }
    
    protected String getPropiedad(String prop){
        return configuracion.getProperty(prop);
    }
    
    protected void setPropiedad(String prop, String valor){
        configuracion.setProperty(prop, valor);
    }
    
    public Properties getPropiedad() {
        return this.configuracion;
    }
}
