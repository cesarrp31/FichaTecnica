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
    private String servidorCorreo,
            puerto;

    public EnviarCorreo(String servidorCorreo, String puerto) {
        this.servidorCorreo = servidorCorreo;
        this.puerto = puerto;
    }

    public EnviarCorreo() {
        this("10.2.0.55","25");
    }
    
    public void enviar(String usuario, String password, 
            String destino, String asunto, String msg) throws MessagingException{
        // Propiedades de la conexión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", servidorCorreo);
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", puerto);
        props.setProperty("mail.smtp.user", usuario);
        props.setProperty("mail.smtp.auth", "true");

        // Preparamos la sesion
        Session session = Session.getDefaultInstance(props);

        // Construimos el mensaje
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(usuario));
        message.addRecipient(
            Message.RecipientType.TO,
            new InternetAddress(destino));
        message.setSubject(asunto);
        message.setText(msg);

        // Lo enviamos.
        Transport transport = session.getTransport("smtp");
        transport.connect(usuario, password);
        transport.sendMessage(message, message.getAllRecipients());

        // Cierre.
        transport.close();
    }

}
