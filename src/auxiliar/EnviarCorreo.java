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
        message.setText(msg);

        // Lo enviamos.
        Transport transport = session.getTransport(propiedad.getProperty("transport"));
        transport.connect(usuario, password);
        transport.sendMessage(message, message.getAllRecipients());

        // Cierre.
        transport.close();
    }
}
