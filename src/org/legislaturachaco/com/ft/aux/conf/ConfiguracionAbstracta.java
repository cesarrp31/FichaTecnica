/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.com.ft.aux.conf;

import org.legislaturachaco.com.ft.aux.GestorArchivo;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author coperalta
 */
public abstract class ConfiguracionAbstracta {
    private final Properties configuracion;
    private String ubicacion;
    public static final String SEPARADOR= File.separator;

    protected ConfiguracionAbstracta(String ubicacion) throws IOException {
        this.ubicacion= ubicacion;
        configuracion = GestorArchivo.obtenerPropiedades(ubicacion);
    }
    
    protected String getPropiedad(String prop){
        return configuracion.getProperty(prop);
    }
    
    protected void setPropiedad(String prop, String valor){
        configuracion.setProperty(prop, valor); 
    }
    
    protected void guardarConfiguraciones() throws IOException{
        GestorArchivo.guardarPropiedades(configuracion, ubicacion);
    }
    
    public Properties getPropiedad() {
        return this.configuracion;
    }
}
