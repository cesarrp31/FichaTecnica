/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.com.gral;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.swing.table.AbstractTableModel;
import org.legislaturachaco.com.exchange.tareas.ITareaExchange;

/**
 *
 * @author Administrador
 */
public class ModeloTablaTareasExchange extends AbstractTableModel{
    public static final String NRO= "Nº", ASUNTO= "Asunto", 
            CUERPO= "Cuerpo Mensaje", FECHA_CREACION= "Fecha de Creación", 
            FECHA_VENCIMIENTO= "Fecha de Vencimiento";
    
    private final List<ITareaExchange> listaTareas;
    private final String [] columnNames= {NRO, ASUNTO, CUERPO, FECHA_CREACION, FECHA_VENCIMIENTO};
    
    public ModeloTablaTareasExchange(List<ITareaExchange> listaTareas){
        this.listaTareas= listaTareas;       
        //this.listaTareas.sort(new ComparadorFechas());
        Collections.sort(this.listaTareas, new ComparadorFechas());
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
        controlarIndices(rowIndex, columnIndex);
        //System.out.println("Valor a devolver: "+rowIndex+":"+columnIndex);
        switch (columnIndex){
            case 0: return rowIndex+1;
            case 1: return this.listaTareas.get(rowIndex).getAsuntoTarea();
            case 2: return this.listaTareas.get(rowIndex).getCuerpoTarea();
            case 3: return this.listaTareas.get(rowIndex).getFechaCreacionTarea();
            case 4: return this.listaTareas.get(rowIndex).getFechaVencimientoTarea();
        }
        throw new RuntimeException("Se ha producido un error.");
    }
    
    @Override
    public Class getColumnClass(int col){
        controlarIndices(0, col);
        return this.getValueAt(0, col).getClass();
    }
    
    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }
    
    public ITareaExchange getTarea(int idx){
        return listaTareas.get(idx);
    }
    
    private void controlarIndices(int rowIndex, int columnIndex){
        int ft= this.getRowCount(), ct= this.getColumnCount();
        if(rowIndex >= ft) throw new RuntimeException("Valor de fila no válido: "+rowIndex+". Máximo valor: "+ft);
        if(columnIndex >= ct) throw new RuntimeException("Valor de columna no válido: "+columnIndex+". Máximo valor: "+ct);
    }
}

class ComparadorFechas implements Comparator<ITareaExchange> {
    @Override
    public int compare(ITareaExchange o1, ITareaExchange o2) {
        if(o1.getFechaCreacionTarea() == null || o1.getFechaCreacionTarea().equals("")) return 0;
        if(o2.getFechaCreacionTarea() == null || o2.getFechaCreacionTarea().equals("")) return 0;
        /*DateFormat df= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.getDefault());
        try {
            Date d1= df.parse(o1.getFechaCreacionTarea()), 
                    d2= df.parse(o2.getFechaCreacionTarea());
            
            return d1.compareTo(d2);
        } catch (ParseException ex) {
            System.err.println("Error al comparar: "+ex.getLocalizedMessage()+
                    ". F1: "+o1.getFechaCreacionTarea()+
                    ". F2: "+o2.getFechaCreacionTarea());
            return o1.getFechaCreacionTarea().compareTo(o2.getFechaCreacionTarea());
        }*/
        
        DateTimeFormatter formateador= DateTimeFormatter.ofPattern("d/M/yyyy h:m:s", Locale.getDefault());
        try{
            LocalDate d1= LocalDate.parse(o1.getFechaCreacionTarea(), formateador),
                  d2= LocalDate.parse(o2.getFechaCreacionTarea(), formateador);
            return d2.compareTo(d1);
        }catch(DateTimeParseException dte){
            System.err.println("Error al comparar: "+dte.getLocalizedMessage()+
                    ". F1: "+o1.getFechaCreacionTarea()+
                    ". F2: "+o2.getFechaCreacionTarea());
            return o2.getFechaCreacionTarea().compareTo(o1.getFechaCreacionTarea());
        }
    }
}