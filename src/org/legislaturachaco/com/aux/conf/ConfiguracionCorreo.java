/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.com.aux.conf;

import java.io.IOException;

/**
 *
 * @author coperalta
 */
public class ConfiguracionCorreo extends ConfiguracionAbstracta{
    
    public ConfiguracionCorreo(String pathArchivo) throws IOException {
        super(pathArchivo);
    }
    
    public String getSmtpHost(){
        return this.getPropiedad("mail.smtp.host");
    }
    
    public String getSmtpStartTlsEnable(){
        return this.getPropiedad("mail.smtp.starttls.enable");
    }
    
    public String getSmtpPort(){
        return this.getPropiedad("mail.smtp.port");
    }
    
    public String getSmtpUser(){
        return this.getPropiedad("mail.smtp.user");
    }
    
    public void setSmtpUser(String usuario){
        this.setPropiedad("mail.smtp.user", usuario);
    }
    
    public String getSmtpAuth(){
        return this.getPropiedad("mail.smtp.auth");
    }
    
    public String getTransport(){
        return this.getPropiedad("conf.transport");
    }
    
    public String getDefaultCorreoEmisor(){
        return this.getPropiedad("default.correo.de");
    }
    
    public String getDefaultCorreoDestinatario(){
        return this.getPropiedad("default.correo.a");
    }
    
    public String getDefaultCorreoAsunto(){
        return this.getPropiedad("default.correo.asunto");
    }
    
    public String getDefaultCorreoUser(){
        return this.getPropiedad("default.correo.deSistema");
    }
}
