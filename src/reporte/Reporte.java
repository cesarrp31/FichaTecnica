/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author coperalta
 */
public class Reporte {
    
    public static void crearReporte(String trabajos, String componentes, String dependencia, 
                                    String patrimonio, String tecnico, String fecha, JFrame ventana){
            try {
           
            File archJasper = new File(Reporte.class.getClassLoader().getResource("fichaTecnica.jasper").getFile());
            System.out.println(archJasper.getAbsoluteFile()+" "+archJasper.exists());
            
            //JasperReport report = JasperCompileManager.compileReport("z:/fichaTecnica.jrxml");
            JasperReport report= (JasperReport)JRLoader.loadObject(archJasper);
            
            Map parametros = new HashMap();
            parametros.put("trabajos", trabajos);
            parametros.put("componentes", componentes);
            parametros.put("dependencia", dependencia);
            parametros.put("patrimonio", patrimonio);
            parametros.put("tecnico", tecnico);
            parametros.put("fecha", fecha);
                        
            JasperPrint print = JasperFillManager.fillReport(report, parametros, new JREmptyDataSource());
            
            JasperViewer jv= new JasperViewer(print, false);
            
            JDialog visor = new JDialog(ventana, ventana.getTitle(), true);
            visor.setContentPane(jv.getContentPane());
            visor.setSize(jv.getSize());
            
            visor.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            visor.setLocationRelativeTo(null);
            visor.setVisible(true);
            
        } catch (JRException ex) {
            System.err.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }
}
