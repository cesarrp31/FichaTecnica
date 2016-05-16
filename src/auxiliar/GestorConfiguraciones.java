/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author coperalta
 */
public class GestorConfiguraciones implements IGestorLectorArchivoTexto{
    private Map<String, String[]> mapConfig;

    public GestorConfiguraciones() {
        mapConfig= new HashMap<>();
    }
    
    public String getValorConfiguracion(String nombre){
        return mapConfig.get(nombre)[0];
    }
    
    public String getDescripcionConfiguracion(String nombre){
        return mapConfig.get(nombre)[1];
    }

    @Override
    public void agregarLinea(String linea) {
        if(linea.startsWith("#")) return;
        String[] valores= linea.split(";"),
                 v= {valores[1], valores[2]};
        this.mapConfig.put(valores[0], v);
    }
}
