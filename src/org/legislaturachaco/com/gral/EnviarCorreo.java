/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.com.gral;

import static org.legislaturachaco.com.ft.FichaTecnica.CONFIG_CORREO;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author coperalta
 */
public class EnviarCorreo {

    public void enviarCorreoSimple(String usuario, String password,
            String destino, String asunto, String msg, String tipoMensaje) throws MessagingException {
        // Propiedades de la conexión
        CONFIG_CORREO.setSmtpUser(usuario);
        String a= ", ";
        System.out.print("Propiedades de Correo PreConfiguradas Cargadas: ");
        
        System.out.print(CONFIG_CORREO.getSmtpHost() + a);
        System.out.print(CONFIG_CORREO.getSmtpStartTlsEnable() + a);
        System.out.print(CONFIG_CORREO.getSmtpPort() + a);
        System.out.print(CONFIG_CORREO.getSmtpUser() + a);
        System.out.println(CONFIG_CORREO.getSmtpAuth());

        // Preparamos la sesion
        Session session = Session.getDefaultInstance(CONFIG_CORREO.getPropiedad());

        // Construimos el mensaje
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(usuario));
        message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(destino));
        message.setSubject(asunto);
        //message.setText(msg);
        message.setContent(msg, tipoMensaje);

        // Lo enviamos.
        Transport transport = session.getTransport(CONFIG_CORREO.getTransport());
        transport.connect(usuario, password);
        transport.sendMessage(message, message.getAllRecipients());

        // Cierre.
        transport.close();
    }

    public void enviarCorreoImagen(String usuario, String password,
            String destino, String asunto, String msg, String pathImg, String tipoMensaje) throws MessagingException {
        // Propiedades de la conexión
        CONFIG_CORREO.setSmtpUser(usuario);
        String a= ", ";
        System.out.println("Propiedades de Correo PreConfiguradas Cargadas: ");
        
        System.out.print(CONFIG_CORREO.getSmtpHost() + a);
        System.out.print(CONFIG_CORREO.getSmtpStartTlsEnable() + a);
        System.out.print(CONFIG_CORREO.getSmtpPort() + a);
        System.out.print(CONFIG_CORREO.getSmtpUser() + a);
        System.out.println(CONFIG_CORREO.getSmtpAuth());

        // Preparamos la sesion
        Session session = Session.getDefaultInstance(CONFIG_CORREO.getPropiedad());

        // Construimos el mensaje
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(usuario));
        message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(destino));
        message.setSubject(asunto);

        MimeMultipart multipart = new MimeMultipart();

        // Texto (html)
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(msg, tipoMensaje);
        multipart.addBodyPart(messageBodyPart);

        // Imagen
        messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource(pathImg);

        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID", "<image>");
        messageBodyPart.setFileName(pathImg);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);
        
        // Lo enviamos.
        Transport transport = session.getTransport(CONFIG_CORREO.getTransport());
        transport.connect(usuario, password);
        transport.sendMessage(message, message.getAllRecipients());

        // Cierre.
        transport.close();
    }
}
