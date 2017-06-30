/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.com.gral;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.legislaturachaco.com.exchange.tareas.ITareaExchange;

import java.nio.charset.Charset;

import org.apache.tika.detect.AutoDetectReader;
import org.apache.tika.exception.TikaException;

/**
 *
 * @author Administrador
 */
public class FormateadorTareaExchange {

    private ITareaExchange objeto;
    private int i;
    
    private Charset chUTF8 = Charset.forName("UTF-8"), 
                    chUTF16 = Charset.forName("UTF-16"),
                    chISO88591= Charset.forName("Windows-1252"),
                    chWin1252= Charset.forName("ISO-8859-1");

    public FormateadorTareaExchange(int i, ITareaExchange objeto) {
        this.objeto = objeto;
        this.i = i;
    }

    @Override
    public String toString() {
        String ch= detectorCodificacion(objeto.getCuerpoTarea());
        //System.out.println(Charset.availableCharsets());
        //String ns= new String(objeto.getCuerpoTarea().getBytes(Charset.forName("Cp1252")), Charset.forName("UTF-16"));
        //String respuesta = i + ": " + ns;

        System.out.println("Valor: "+objeto.toString()+". Enc: "+ch+". Default "+Charset.defaultCharset());

        //return respuesta;
        
        return objeto.getCuerpoTarea();
    }

    public static String detectorCodificacion(String in) {
        return detectorCodificacionCharset(in).displayName();
    }
    
    public static Charset detectorCodificacionCharset(String in) {
        try {

            // convert String into InputStream
            InputStream is = new ByteArrayInputStream(in.getBytes());

            Charset charset = new AutoDetectReader(is).getCharset();

            // read it with BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
            }
            br.close();

            return charset;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TikaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Charset.defaultCharset();
    }
    
    public static String stringCorrectos(String in){
        return new String (in.getBytes(detectorCodificacionCharset(in)));
    }
}
