/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.com.gral;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

/**
 *
 * @author Administrador
 */
public class MouseSobreTablaAdapter extends MouseAdapter{
    private final JTable tabla;

    public MouseSobreTablaAdapter(JTable tabla) {
        this.tabla = tabla;
    }    
    
    @Override
    public void mouseMoved(MouseEvent e) {
        Point p= e.getPoint();
        int f= tabla.rowAtPoint(p),
            c= tabla.columnAtPoint(p);
        StringBuilder sb= new StringBuilder();
        boolean filaValida= (f>-1)&&(f<tabla.getRowCount()),
                columnaValida= (c>-1)&&(c<tabla.getColumnCount());
        if(filaValida && columnaValida){
            //tabla.setToolTipText(f+":"+c);
            sb.append("<html>");
            sb.append(f);
            sb.append(":");
            sb.append(c);
            for(int idx= 1; idx < tabla.getColumnCount(); idx++){
                sb.append("<br>");  
                sb.append("<h4>");
                sb.append(tabla.getColumnName(idx));
                sb.append(": ");
                sb.append("</h4>");
                sb.append(tabla.getValueAt(f, idx));
            }
            sb.append("</html>");
            tabla.setToolTipText(sb.toString());
        }        
    }
}
