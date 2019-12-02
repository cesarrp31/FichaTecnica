/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.legislaturachaco.com.gral;

import java.util.Properties;
import static org.legislaturachaco.ft.FichaTecnica.CONFIG_CORREO;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author coperalta
 */
public class EnviarCorreo {
    private Properties propiedades;
    
    public EnviarCorreo() {
        this.propiedades= CONFIG_CORREO.getPropiedad();
    }

    
    public EnviarCorreo(Properties propiedades) {
        this.propiedades= propiedades;
    }

    public void enviarCorreoSimple(String usuario, String password,
            String destino, String asunto, String msg, String tipoMensaje) throws MessagingException {
        String usuarioCompleto= usuario+"@legislaturachaco.gov.ar";

        // Propiedades de la conexión
        this.mostrarPropiedades(propiedades);

        // Preparamos la sesion
        Session session = this.getSession(usuarioCompleto, password);

        // Construimos el mensaje
        MimeMessage message = getMimeMessage(session, usuarioCompleto, destino, asunto);
        
        //Cargar Contenido
        message.setContent(msg, tipoMensaje);

        // Lo enviamos.
        Transport transport = session.getTransport(CONFIG_CORREO.getTransport());
        transport.connect(CONFIG_CORREO.getSmtpHost(), usuario, password);
        transport.sendMessage(message, message.getAllRecipients());

        // Cierre.
        transport.close();
    }

    public void enviarCorreoImagen(String usuario, String password,
            String destino, String asunto, String msg, String pathImg, String tipoMensaje) throws MessagingException {
        String usuarioCompleto= usuario+"@legislaturachaco.gov.ar";
        
        propiedades.put("mail.smtp.user", usuarioCompleto);
        propiedades.put("mail.smtp.from", usuarioCompleto);
        propiedades.put("mail.smtp.submitter", usuarioCompleto);
        // Propiedades de la conexión
        this.mostrarPropiedades(propiedades);
        
        // Preparamos la sesion
        Session session = this.getSession(usuarioCompleto, password);

        // Construimos el mensaje
        MimeMessage message = getMimeMessage(session, usuarioCompleto, destino, asunto);
        
        //Cargar Contenido
        MimeMultipart multipart = new MimeMultipart();

        //  Texto (html)
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(msg, tipoMensaje);
        multipart.addBodyPart(messageBodyPart);

        //  Imagen
        messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource(pathImg);

        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID", "<image>");
        messageBodyPart.setFileName(pathImg);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);
        
        // Lo enviamos.
        Transport transport = session.getTransport(CONFIG_CORREO.getTransport());
        transport.connect(CONFIG_CORREO.getSmtpHost(), new Integer(CONFIG_CORREO.getSmtpPort()), usuario, password);
        transport.sendMessage(message, message.getAllRecipients());

        // Cierre.
        transport.close();
    }
    
    private void mostrarPropiedades(Properties propiedades){
        System.out.println("Propiedades de Correo PreConfiguradas Cargadas: ");
        
        propiedades.forEach((k,v) -> System.out.println(k+":"+v));
    }

    private Session getSession(String usuario, String password) {
        return Session.getInstance(CONFIG_CORREO.getPropiedad(),
                new Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(usuario, password);
                    }
                });
    }
    
    private MimeMessage getMimeMessage(Session session, String usuario, String destino, String asunto) 
            throws AddressException, MessagingException{
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(usuario));
        message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(destino));
        message.setSubject(asunto);
        message.setSender(new InternetAddress(usuario));
        return message;
    }
}