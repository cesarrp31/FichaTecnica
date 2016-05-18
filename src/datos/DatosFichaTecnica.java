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
    public static final String SEPARADOR= "\"", DELIMITADOR= ";";
    private final String dependencia, fecha, patrimonio, tarea, componentes, 
                         tecnico, nroNota, ponderacion, estado;

    public DatosFichaTecnica(String dependencia, String fecha, String nroNota,
                             String patrimonio, String ponderacion, String estado,
                             String tarea, String componentes, String tecnico) {
        this.dependencia = dependencia;
        this.fecha = fecha;
        this.patrimonio = patrimonio;
        this.tarea = tarea;
        this.componentes = componentes;
        this.tecnico = tecnico;
        this.nroNota = nroNota;
        this.ponderacion = ponderacion;
        this.estado = estado;
    }

    public static String getSEPARADOR() {
        return SEPARADOR;
    }

    public static String getDELIMITADOR() {
        return DELIMITADOR;
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

    public String getNroNota() {
        return nroNota;
    }

    public String getPonderacion() {
        return ponderacion;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.dependencia);
        hash = 67 * hash + Objects.hashCode(this.fecha);
        hash = 67 * hash + Objects.hashCode(this.patrimonio);
        hash = 67 * hash + Objects.hashCode(this.tarea);
        hash = 67 * hash + Objects.hashCode(this.componentes);
        hash = 67 * hash + Objects.hashCode(this.tecnico);
        hash = 67 * hash + Objects.hashCode(this.nroNota);
        hash = 67 * hash + Objects.hashCode(this.ponderacion);
        hash = 67 * hash + Objects.hashCode(this.estado);
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
        if (!Objects.equals(this.nroNota, other.nroNota)) {
            return false;
        }
        if (!Objects.equals(this.ponderacion, other.ponderacion)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        return true;
    }

    private String getCampo(String campo){
        return SEPARADOR + campo + SEPARADOR + DELIMITADOR;
    }
            
    @Override
    public String toString() {
        return getCampo(dependencia) + getCampo(fecha) + getCampo(nroNota) +
               getCampo(patrimonio) + getCampo(ponderacion) + getCampo(estado) +
               getCampo(tarea) + getCampo(componentes) + getCampo(tecnico);
    }
}
