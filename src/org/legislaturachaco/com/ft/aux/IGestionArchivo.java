/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.com.ft.aux;

import java.awt.Frame;
import java.io.File;

/**
 *
 * @author coperalta
 */
public interface IGestionArchivo {
    void guardar(File fichero);
    
    void abrir(File fichero);
    
    Frame ventana();
    
    String extensionArchivo();
    
    String descripcionTipoArchivo();
    
    String carpetaGuardado();
}
