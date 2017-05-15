/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.com.aux;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.legislaturachaco.com.ft.FichaTecnica;
import static org.legislaturachaco.com.ft.FichaTecnica.CONFIG_GENERAL;
import java.awt.image.BufferedImage;
import java.nio.file.Path;

public final class CodigoQR {
    public static final int DEFAULT_ALTO_CODIGO_QR= 120, 
                            DEFAULT_ANCHO_CODIGO_QR= 120;
    
    private Map<EncodeHintType, ErrorCorrectionLevel> hintMap;
    private String charset;
    private int anchoCodigoQR, altoCodigoQR;
    
    public CodigoQR(){
       this(CONFIG_GENERAL.getConfiguracionCodificacion(), DEFAULT_ALTO_CODIGO_QR, DEFAULT_ANCHO_CODIGO_QR); 
    }
    
    public CodigoQR(String charset, int alto, int ancho){
        this.charset=charset;
        anchoCodigoQR= ancho; 
        altoCodigoQR= alto;
        hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
    }

    public void crearCodigoQR(String datos, String pathArchivo)
            throws WriterException, IOException {
        
        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(datos.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, anchoCodigoQR, altoCodigoQR, hintMap);
        Path path = (new File(pathArchivo)).toPath();
        MatrixToImageWriter.
                writeToPath(matrix, 
                            pathArchivo.substring(pathArchivo.lastIndexOf('.') + 1), 
                            path);
    }

    public String leerCodigoQR(String pathArchivo)
            throws FileNotFoundException, IOException, NotFoundException {
        
        Map hintMap= this.hintMap;
        BufferedImage bi= ImageIO.read(new FileInputStream(pathArchivo));
        BufferedImageLuminanceSource bils= new BufferedImageLuminanceSource(bi);
        BinaryBitmap bb = new BinaryBitmap(new HybridBinarizer(bils));
        Result qrResultado = new MultiFormatReader().decode(bb,hintMap);
        return qrResultado.getText();
    }
    
    public static void main(String[] args) throws WriterException, IOException,
            NotFoundException {
        String qrDatos = "Direccion de Comunicaciones";
        String pathArchivo = "Z:/QRCodigo25.png";
        CodigoQR qr= new CodigoQR();

        qr.crearCodigoQR(qrDatos, pathArchivo);
        System.out.println("QR Code image created successfully!");

        System.out.println("Data read from QR Code: "
                + qr.leerCodigoQR(pathArchivo));
    }
}