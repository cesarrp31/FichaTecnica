/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

import java.util.Properties;
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

    private Properties propiedad;

    public EnviarCorreo(Properties propiedad) {
        this.propiedad = propiedad;
    }

    public void enviarCorreoSimple(String usuario, String password,
            String destino, String asunto, String msg, String tipoMensaje) throws MessagingException {
        // Propiedades de la conexión
        propiedad.setProperty("mail.smtp.user", usuario);

        System.out.println("Propiedades de Correo PreConfiguradas Cargadas: ");
        //Properties propiedad = new Properties();
        System.out.println(propiedad.getProperty("mail.smtp.host"));
        System.out.println(propiedad.getProperty("mail.smtp.starttls.enable"));
        System.out.println(propiedad.getProperty("mail.smtp.port"));
        System.out.println(propiedad.getProperty("mail.smtp.user"));
        System.out.println(propiedad.getProperty("mail.smtp.auth"));

        // Preparamos la sesion
        Session session = Session.getDefaultInstance(propiedad);

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
        Transport transport = session.getTransport(propiedad.getProperty("transport"));
        transport.connect(usuario, password);
        transport.sendMessage(message, message.getAllRecipients());

        // Cierre.
        transport.close();
    }

    public void enviarCorreoImagen(String usuario, String password,
            String destino, String asunto, String msg, String pathImg, String tipoMensaje) throws MessagingException {
        // Propiedades de la conexión
        propiedad.setProperty("mail.smtp.user", usuario);

        System.out.println("Propiedades de Correo PreConfiguradas Cargadas: ");
        //Properties propiedad = new Properties();
        System.out.println(propiedad.getProperty("mail.smtp.host"));
        System.out.println(propiedad.getProperty("mail.smtp.starttls.enable"));
        System.out.println(propiedad.getProperty("mail.smtp.port"));
        System.out.println(propiedad.getProperty("mail.smtp.user"));
        System.out.println(propiedad.getProperty("mail.smtp.auth"));

        // Preparamos la sesion
        Session session = Session.getDefaultInstance(propiedad);

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
        Transport transport = session.getTransport(propiedad.getProperty("conf.transport"));
        transport.connect(usuario, password);
        transport.sendMessage(message, message.getAllRecipients());

        // Cierre.
        transport.close();
    }
}
