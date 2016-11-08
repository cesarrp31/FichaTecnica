/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar.configuraciones;

import java.io.IOException;

/**
 *
 * @author coperalta
 */
public class ConfiguracionGeneral extends ConfiguracionAbstracta{
    public static final String APLICACION_NOMBRE= "SRTI: Sistema de Registro de Tareas Informáticas",
                               APLICACION_VERSION= "3.1",
                               APLICACION_ANIO= "2016";

    public ConfiguracionGeneral() throws IOException {
        super("config\\configuracionInicial.config");
    }
    
    public String getArchivoConfiguracionCorreo(){
        return this.getPropiedad("cnf.Correo");
    }
    
    public String getCarpetaConfiguracionCorreo(){
        return this.getPropiedad("crp.configCorreo");
    }
    
    public String getCarpetaImagenes(){
        return this.getPropiedad("crp.imagenes");
    }
    
    public String getCarpetaRecursos(){
        return this.getPropiedad("crp.recursos");
    }
    
    public String getCarpetaTemporal(){
        return this.getPropiedad("crp.temp");
    }
    
    public String getCarpetaGuardado(){
        return this.getPropiedad("crp.guardado");
    }
    
    public String getImagenBotonEnviar(){
        return this.getPropiedad("btn.imag.enviar");
    }
    
    public String getImagenBotonImprimir(){
        return this.getPropiedad("btn.imag.imprimir");
    }
    
    public String getImagenBotonNuevo(){
        return this.getPropiedad("btn.imag.nuevo");
    }
    
    public String getImagenBotonAbrir(){
        return this.getPropiedad("btn.imag.abrir");
    }
    
    public String getImagenBotonGuardar(){
        return this.getPropiedad("btn.imag.guardar");
    }
    
    public String getImagenLogoApp(){
        return this.getPropiedad("imag.logoApp");
    }
    
    public String getImagenIconoApp(){
        return this.getPropiedad("imag.iconoApp");
    }
    
    public String getImagenCorreoPie(){
        return this.getPropiedad("imag.iconoAppMail");
    }
    
    public String getImagenCodigoQR(){
        return this.getPropiedad("imag.codigoQR");
    }
    
    public String getConfiguracionFormatoFecha(){
        return this.getPropiedad("conf.formatoFecha");
    }
    
    public String getConfiguracionArchivoExtension(){
        return this.getPropiedad("conf.extArchivoFichaTecnica");
    }
    
    public String getConfiguracionArchivoDescripcion(){
        return this.getPropiedad("conf.descArchivoFichaTecnica");
    }
    
    public String getConfiguracionSeparadorCampos(){
        return this.getPropiedad("conf.separadorCampos");
    }
    
    public String getConfiguracionCantidadCaracteresComboBox(){
        return this.getPropiedad("conf.cantCaracteresComboBox");
    }
    
    public String getConfiguracionCantidadCaracteresTextField(){
        return this.getPropiedad("conf.cantCaracteresTextField");
    }
    
    public String getConfiguracionCodificacion(){
        return this.getPropiedad("conf.codificacion");
    }
    
    public String getNombreArchivoTareas(){
        return this.getPropiedad("val.tareas");
    }
    
    public String getNombreArchivoComponentes(){
        return this.getPropiedad("val.componentes");
    }
    
    public String getNombreArchivoDependencias(){
        return this.getPropiedad("val.dependencias");
    }
    
    public String getNombreArchivoEstados(){
        return this.getPropiedad("val.estados");
    }
    
    public String getNombreArchivoPonderaciones(){
        return this.getPropiedad("val.ponderaciones");
    }

    public String getValorColumnaConfiguracion1(){
        return this.getPropiedad("val.columna.configuracion1");
    }
    
    public String getValorColumnaConfiguracion2(){
        return this.getPropiedad("val.columna.configuracion2");
    }
    
    public String getValorColumnaConfiguracion3(){
        return this.getPropiedad("val.columna.configuracion3");
    }
    
    public String getArchivoInforme(){
        return this.getPropiedad("info.jasper.FichaTecnica");
    }
    
    public String getDefaultCargarNombreUsuarioSesion(){
        return this.getPropiedad("default.appNom.cargarSesion");
    }
    
    public String getImagenBotonEnviar24(){
        return this.getPropiedad("btn.imag.enviar24");
    }
    
    public String getImagenBotonImprimir24(){
        return this.getPropiedad("btn.imag.imprimir24");
    }
    
    public String getImagenBotonNuevo24(){
        return this.getPropiedad("btn.imag.nuevo24");
    }
    
    public String getImagenBotonAbrir24(){
        return this.getPropiedad("btn.imag.abrir24");
    }
    
    public String getImagenBotonGuardar24(){
        return this.getPropiedad("btn.imag.guardar24");
    }
    
    public String getImagenBotonSalir24(){
        return this.getPropiedad("btn.imag.salir24");
    }
    
    public String getImagenLogoBarra(){
        return this.getPropiedad("btn.imag.barra");
    }
    
    public String getCorreoEnviarTabla(){
        return this.getPropiedad("correo.env.tabla");
    }
}
