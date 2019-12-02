/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.ft.ventanas;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author cesar
 */
public class FichaTecnicaImpresionYTareas extends FichaTecnicaImpresion {
    private final String tipoDLL;

    public FichaTecnicaImpresionYTareas(String tipoDLL) throws FileNotFoundException, IOException {
        super();
        this.tipoDLL= tipoDLL;
    }

    public String getTipoDLL() {
        return tipoDLL;
    }

}
