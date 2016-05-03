/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author coperalta
 */
public class EnviarCorreo {
    private Properties propiedad;

    public EnviarCorreo(Properties propiedad) {
        this.propiedad= propiedad;
    }
    
    public void enviar(String usuario, String password, 
            String destino, String asunto, String msg) throws MessagingException{
        // Propiedades de la conexión
        propiedad.setProperty("mail.smtp.user", usuario);
        /*
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", servidorCorreo);
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", puerto);
        propiedad.setProperty("mail.smtp.user", usuario);
        propiedad.setProperty("mail.smtp.auth", "true");
        */
        // Preparamos la sesion
        Session session = Session.getDefaultInstance(propiedad);

        // Construimos el mensaje
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(usuario));
        message.addRecipient(
            Message.RecipientType.TO,
            new InternetAddress(destino));
        message.setSubject(asunto);
        message.setText(msg);

        // Lo enviamos.
        Transport transport = session.getTransport(propiedad.getProperty("transport"));
        transport.connect(usuario, password);
        transport.sendMessage(message, message.getAllRecipients());

        // Cierre.
        transport.close();
    }
}
