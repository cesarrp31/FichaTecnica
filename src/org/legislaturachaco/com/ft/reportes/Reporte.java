/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.com.ft.reportes;

import org.legislaturachaco.com.aux.GestorArchivo;
import org.legislaturachaco.com.ft.datos.DatosFichaTecnica;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import static org.legislaturachaco.com.ft.FichaTecnica.CONFIG_GENERAL;

/**
 *
 * @author coperalta
 */
public class Reporte {
    private static JasperReport report;
    private static String nombJasper;
    private JDialog visor;
    
    public static void prepararReporte(){
        String crpRec= CONFIG_GENERAL.getCarpetaRecursos()+GestorArchivo.SEPARADOR;
        
        try{
            nombJasper=crpRec+CONFIG_GENERAL.getArchivoInforme();
            File archJasper = GestorArchivo.cargarArchivo(nombJasper);
            report = (JasperReport) JRLoader.loadObject(archJasper);
            
            Map parametros = new HashMap();
            JasperPrint print = JasperFillManager.fillReport(report, parametros, new JREmptyDataSource());
            
        } catch (Exception ex) {
            System.err.println("Archivo: "+nombJasper+". "+ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

    public static void crearReporte(DatosFichaTecnica dft, JFrame ventana) {
        try {
            Map parametros = new HashMap();
            parametros.put("trabajos", dft.getTarea().trim());
            parametros.put("componentes", dft.getComponentes().trim());
            parametros.put("dependencia", dft.getDependencia().trim());
            parametros.put("patrimonio", dft.getPatrimonio().trim());
            parametros.put("tecnico", dft.getTecnico().trim());
            parametros.put("fecha", dft.getFecha().trim());
            parametros.put("nota", dft.getNroNota().trim());
            parametros.put("ponderacion", dft.getPonderacion().trim());
            parametros.put("estado", dft.getEstado().trim());
            parametros.put("usuario", dft.getUsuario().trim());
            parametros.put("clave", dft.getClave().trim());
            parametros.put("solicitud", dft.getIngresoSeleccionado().trim());

            JasperPrint print = JasperFillManager.fillReport(report, parametros, new JREmptyDataSource());

            JasperViewer jv = new JasperViewer(print, false);
            JDialog visor = new JDialog(ventana, ventana.getTitle(), true);
            visor.setContentPane(jv.getContentPane());
            visor.setSize(jv.getSize());

            visor.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            visor.setLocationRelativeTo(null);
            visor.setVisible(true);
            
        } catch (Exception ex) {
            System.err.println("Archivo: "+nombJasper+". "+ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }
}
