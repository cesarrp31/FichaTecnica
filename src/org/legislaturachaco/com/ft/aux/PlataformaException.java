/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.com.ft.aux;

/**
 *
 * @author cesar
 */
public class PlataformaException extends RuntimeException{
    
    public PlataformaException(){
        super("El entorno de ejecución no es el esperado. Entorno actual: "
                + "OS "+GestorEntornoEjecucion.getSOComputadora()+
                ", Versión "+GestorEntornoEjecucion.getVersionSOComputadora());
    }
    
    public PlataformaException(String msg){
        super(msg);
    }
}
