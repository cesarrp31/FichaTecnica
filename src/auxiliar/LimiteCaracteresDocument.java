/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimiteCaracteresDocument extends PlainDocument {

    private int limiteCaracteres = 10;

    public LimiteCaracteresDocument(int limiteCaracteres) {
        this.limiteCaracteres = limiteCaracteres;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {
        /*
        if (getLength() + str.length() > limiteCaracteres) {
            str = str.substring(0, limiteCaracteres - getLength());
        }
        super.insertString(offs, str, a);
        */
        if (str == null) return;

        if ((getLength() + str.length()) <= limiteCaracteres) {
          super.insertString(offs, str, a);
        }
    }
}
