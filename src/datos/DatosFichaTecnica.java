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
    private final String usuario, clave, ingresoSeleccionado;

    public DatosFichaTecnica(String dependencia, String fecha, String nroNota,
                             String patrimonio, String ponderacion, String estado,
                             String tarea, String componentes, String tecnico,
                             String usuario, String clave, String ingresoSeleccionado) {
        this.dependencia = dependencia;
        this.fecha = fecha;
        this.patrimonio = patrimonio;
        this.tarea = tarea;
        this.componentes = componentes;
        this.tecnico = tecnico;
        this.nroNota = nroNota;
        this.ponderacion = ponderacion;
        this.estado = estado;
        this.usuario= usuario;
        this.clave= clave;
        this.ingresoSeleccionado= ingresoSeleccionado;
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

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    public String getIngresoSeleccionado() {
        return ingresoSeleccionado;
    }
    
    private String getCampo(String campo){
        return SEPARADOR + campo + SEPARADOR + DELIMITADOR;
    }
        
    @Override
    public String toString() {
        return getCampo(dependencia) + getCampo(fecha) + getCampo(nroNota) +
               getCampo(patrimonio) + getCampo(ponderacion) + getCampo(estado) +
               getCampo(tarea) + getCampo(componentes) + getCampo(tecnico) + 
               getCampo(usuario) + getCampo(clave) + getCampo(ingresoSeleccionado);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.dependencia);
        hash = 47 * hash + Objects.hashCode(this.fecha);
        hash = 47 * hash + Objects.hashCode(this.patrimonio);
        hash = 47 * hash + Objects.hashCode(this.tarea);
        hash = 47 * hash + Objects.hashCode(this.componentes);
        hash = 47 * hash + Objects.hashCode(this.tecnico);
        hash = 47 * hash + Objects.hashCode(this.nroNota);
        hash = 47 * hash + Objects.hashCode(this.ponderacion);
        hash = 47 * hash + Objects.hashCode(this.estado);
        hash = 47 * hash + Objects.hashCode(this.usuario);
        hash = 47 * hash + Objects.hashCode(this.clave);
        hash = 47 * hash + Objects.hashCode(this.ingresoSeleccionado);
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
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.clave, other.clave)) {
            return false;
        }
        if (!Objects.equals(this.ingresoSeleccionado, other.ingresoSeleccionado)) {
            return false;
        }
        return true;
    }
    
}
