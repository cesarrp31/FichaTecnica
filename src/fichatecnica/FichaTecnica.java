/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fichatecnica;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ventanas.FichaTecnicaImpresion;

/**
 *
 * @author coperalta
 */
public class FichaTecnica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            FichaTecnicaImpresion ft=new FichaTecnicaImpresion();
            ft.pack();
            ft.setLocationRelativeTo(null);
            ft.setVisible(true);
        } catch (FileNotFoundException ex) {
            System.err.println("Error: "+ex.getLocalizedMessage());
        }
    }
    
}
