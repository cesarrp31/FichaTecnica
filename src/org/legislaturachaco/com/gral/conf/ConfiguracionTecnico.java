/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.com.gral.conf;

import java.io.IOException;

/**
 *
 * @author coperalta
 */
public class ConfiguracionTecnico extends ConfiguracionAbstracta{
    
    public ConfiguracionTecnico() throws IOException {
        super("configuracionTecnico.config");
    }
    
    public String getNombreTecnico(){
        return this.getPropiedad("default.appNom.tecnico");
    }
    
    public void setNombreTecnico(String nombreTecnico) throws IOException{
        this.setPropiedad("default.appNom.tecnico", nombreTecnico);
        
        this.guardarConfiguraciones();
    }
    
    public String getUsuarioTecnico(){
        return this.getPropiedad("default.usuario.tecnico");
    }
    
    public void setUsuarioTecnico(String usuarioTecnico) throws IOException{
        this.setPropiedad("default.usuario.tecnico", usuarioTecnico);
        
        this.guardarConfiguraciones();
    }    
}
