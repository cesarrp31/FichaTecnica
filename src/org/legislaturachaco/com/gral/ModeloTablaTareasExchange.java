/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.com.gral;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.legislaturachaco.com.exchange.tareas.ITareaExchange;

/**
 *
 * @author Administrador
 */
public class ModeloTablaTareasExchange extends AbstractTableModel{
    public static final String NRO= "Nº", CUERPO= "Cuerpo";
    
    private final List<ITareaExchange> listaTareas;
    private final String [] columnNames= {NRO, CUERPO};
    
    public ModeloTablaTareasExchange(List<ITareaExchange> listaTareas){
        this.listaTareas= listaTareas;
    }

    @Override
    public int getRowCount() {
        return this.listaTareas.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int ft= this.getRowCount(), ct= this.getColumnCount();
        if(rowIndex >= ft) throw new RuntimeException("Valor de fila no válido: "+rowIndex+". Máximo valor: "+ft);
        if(columnIndex >= ct) throw new RuntimeException("Valor de columna no válido: "+columnIndex+". Máximo valor: "+ct);
        System.out.println("Valora devolver: "+rowIndex+":"+columnIndex);
        switch (columnIndex){
            case 0: return rowIndex;
            case 1: return this.listaTareas.get(rowIndex).getCuerpoTarea();
        }
        throw new RuntimeException("Se ha producido un error.");
    }
    
    @Override
    public Class getColumnClass(int col){
        return this.getValueAt(0, col).getClass();
    }
    
    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }
}
