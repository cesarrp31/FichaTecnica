/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author coperalta
 */
public class Dependencia {
    private String id;
    private String dependencia;

    public Dependencia(String id, String dependencia) {
        this.id = id;
        this.dependencia = dependencia;
    }

    public Dependencia(String dependencia) {
        this("0", dependencia);
    }

    @Override
    public String toString() {
        return dependencia;
    }    

    public String getId() {
        return id;
    }

    public String getDependencia() {
        return dependencia;
    }
    
}
