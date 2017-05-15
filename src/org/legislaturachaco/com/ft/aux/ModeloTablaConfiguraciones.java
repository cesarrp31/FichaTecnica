/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.com.ft.aux;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author coperalta
 */
public class ModeloTablaConfiguraciones extends DefaultTableModel{
    private final String [] nombres;

    public ModeloTablaConfiguraciones(int row, int col, String [] nombres) {
        super(row, col);
        this.nombres= nombres;
    }
    
    @Override
    public String getColumnName(int index) {
        return nombres[index];
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        if(col==2) return true;
        return false;
    }
}
