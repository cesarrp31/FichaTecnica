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
public class GestorEntornoEjecucion {
    public static final String ARQ_64_BIT= "amd64", 
                                ARQ_32_BIT="x86",
                                WINDOWS= "Windows";    
    
    private GestorEntornoEjecucion(){}
    
    public static String getArcComputadora(){
        return System.getProperty("os.arch");
    }
    
    public static String getModeloDatos(){
        return System.getProperty("sun.arch.data.model");
    }
    
    public static String getSOComputadora(){
        return System.getProperty("os.name");
    }
    
    public static String getVersionSOComputadora(){
        return System.getProperty("os.version");
    }
    
    /**
     * Devuelve la carpeta de ejecución (o trabajo) actual.
     * @return 
     */
    public static String getCarpetaTrabajo(){
        return System.getProperty("user.dir");
    }
    
    public static String getCarpetaUsuario(){
        return System.getProperty("user.home");
    }
    
    public static String getNombreUsuario(){
        return System.getProperty("user.name");
    }
    
    public static String getSeparadorCarpetas(){
        return System.getProperty("file.separator");
    }
    
    public static void main(String [] args){
        System.err.println(GestorEntornoEjecucion.getArcComputadora());
        System.err.println(GestorEntornoEjecucion.getModeloDatos());
        System.err.println(GestorEntornoEjecucion.getSOComputadora());
        System.err.println(GestorEntornoEjecucion.getNombreUsuario());
        System.err.println(GestorEntornoEjecucion.getCarpetaTrabajo());
        System.err.println(GestorEntornoEjecucion.getCarpetaUsuario());
        System.err.println(GestorEntornoEjecucion.getVersionSOComputadora());
        System.err.println(GestorEntornoEjecucion.getSeparadorCarpetas());
    }            
    
}
