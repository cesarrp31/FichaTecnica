/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.Objects;

/**
 *
 * @author coperalta
 */
public class DatosFichaTecnica {
    private static final String DELIMITADOR= ";";
    private final String dependencia, fecha, patrimonio, tarea, componentes, tecnico;

    public DatosFichaTecnica(String dependencia, String fecha, String patrimonio, String tarea, String componentes, String tecnico) {
        this.dependencia = dependencia;
        this.fecha = fecha;
        this.patrimonio = patrimonio;
        this.tarea = tarea;
        this.componentes = componentes;
        this.tecnico = tecnico;
    }

    public String getDependencia() {
        return dependencia;
    }

    public String getFecha() {
        return fecha;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public String getTarea() {
        return tarea;
    }

    public String getComponentes() {
        return componentes;
    }

    public String getTecnico() {
        return tecnico;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.dependencia);
        hash = 97 * hash + Objects.hashCode(this.fecha);
        hash = 97 * hash + Objects.hashCode(this.patrimonio);
        hash = 97 * hash + Objects.hashCode(this.tarea);
        hash = 97 * hash + Objects.hashCode(this.componentes);
        hash = 97 * hash + Objects.hashCode(this.tecnico);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DatosFichaTecnica other = (DatosFichaTecnica) obj;
        if (!Objects.equals(this.dependencia, other.dependencia)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.patrimonio, other.patrimonio)) {
            return false;
        }
        if (!Objects.equals(this.tarea, other.tarea)) {
            return false;
        }
        if (!Objects.equals(this.componentes, other.componentes)) {
            return false;
        }
        if (!Objects.equals(this.tecnico, other.tecnico)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String separador= "\"";
        return separador + dependencia + separador + DELIMITADOR + separador + fecha + separador + DELIMITADOR + 
               separador + patrimonio + separador + DELIMITADOR + separador + tarea + separador + DELIMITADOR + 
               separador + componentes + separador + DELIMITADOR + separador + tecnico + separador+ DELIMITADOR;
    }
}
