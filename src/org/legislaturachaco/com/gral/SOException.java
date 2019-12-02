/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.com.gral;

/**
 *
 * @author cesar
 */
public class SOException extends RuntimeException{
    
    public SOException(){
        this("Se ha detectado un s.o. desconocido. SO actual: "
                + GestorEntornoEjecucion.getSOComputadora()
                + ", Versión "+GestorEntornoEjecucion.getVersionSOComputadora());
    }
    
    public SOException(String msg){
        super(msg);
    }    
}