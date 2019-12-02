/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.ft.datos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cesar
 */
public class GestoresDatos {
    
    private final List<Gestor> gestores;

    public GestoresDatos() {
        gestores= new ArrayList<Gestor>();
    }
        
    public void agregarGestor(Gestor gestor){
        gestores.add(gestor);        
    }
}
