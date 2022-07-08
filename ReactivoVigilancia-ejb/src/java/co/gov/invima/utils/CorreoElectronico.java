/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.utils;

import co.gov.invima.VO.UsuariosVO;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Priority;

/**
 * Calse encargada de enviar correos electrónicos
 *
 * @author mgualdrond
 */
public class CorreoElectronico {

    private final static Logger logEJBReactivo = Logger.getLogger(CorreoElectronico.class.getName());

    public Boolean enviarCorreo(String asunto, String mensaje, String destino, UsuariosVO usuarioReactivovigilancia, String Operacion) {
        Boolean b = false;
        try {
            PropertiesReader pr = new PropertiesReader("co.gov.invima.utils.mail");

            Properties props = new Properties();
            props.setProperty("mail.smtp.host", pr.getProperty("host"));
            props.setProperty("mail.smtp.starttls.enable", pr.getProperty("ssl"));
            props.setProperty("mail.smtp.port", pr.getProperty("puerto"));
            props.setProperty("mail.smtp.auth", pr.getProperty("autenthication"));
            Session session = Session.getDefaultInstance(props);
            String from = pr.getProperty("from");
            String paswword = pr.getProperty("password");
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            Address[] receptor;
            receptor = new Address[]{new InternetAddress(destino)};
            message.addRecipients(Message.RecipientType.TO, receptor);
            message.setSubject(asunto, "ISO-8859-1");
            //message.setText(mensaje, "ISO-8859-1");
            message.setContent(mensaje, "text/html");
            //Transport t = session.getTransport(pr.getProperty("protocol"));
            //t.connect(pr.getProperty("user"), paswword);
            //t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            //t.close();
            logCorreo(pr.getProperty("host"), from, "Sin Autenticacion", "", destino, "", asunto, mensaje, usuarioReactivovigilancia, Operacion,0);
            Transport.send(message, message.getRecipients(Message.RecipientType.TO));
            logEJBReactivo.log(Priority.INFO, "Enviando correo Electrónico");
            b = true;
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al enciar correo electrónico: ", e);
        } finally {
            return b;
        }

    }
    
    public Boolean enviarCorreo(String asunto, String mensaje, String destino,String copia, UsuariosVO usuarioReactivovigilancia, String Operacion) {
        Boolean b = false;
        try {
            PropertiesReader pr = new PropertiesReader("co.gov.invima.utils.mail");

            Properties props = new Properties();
            props.setProperty("mail.smtp.host", pr.getProperty("host"));
            props.setProperty("mail.smtp.starttls.enable", pr.getProperty("ssl"));
            props.setProperty("mail.smtp.port", pr.getProperty("puerto"));
            props.setProperty("mail.smtp.auth", pr.getProperty("autenthication"));
            Session session = Session.getDefaultInstance(props);
            String from = pr.getProperty("from");
            String paswword = pr.getProperty("password");
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            Address[] receptor;
            receptor = new Address[]{new InternetAddress(destino),new InternetAddress(copia)};
            message.addRecipients(Message.RecipientType.TO, receptor);
            message.setSubject(asunto, "ISO-8859-1");
            //message.setText(mensaje, "ISO-8859-1");
            message.setContent(mensaje, "text/html");
            //Transport t = session.getTransport(pr.getProperty("protocol"));
            //t.connect(pr.getProperty("user"), paswword);
            //t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            //t.close();
            logCorreo(pr.getProperty("host"), from, "Sin Autenticacion", "", destino, copia, asunto, mensaje, usuarioReactivovigilancia, Operacion,0);
            Transport.send(message, message.getRecipients(Message.RecipientType.TO));
            logEJBReactivo.log(Priority.INFO, "Enviando correo Electrónico");
            b = true;
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al enciar correo electrónico: ", e);
        } finally {
            return b;
        }

    }
    private final static Logger logCorreoEnviadosReactivovigilancia = Logger.getLogger(CorreoElectronico.class);
    public  void logCorreo(String unServidorCorreo, String unRemitente, String usuario, String password, String unDestinatario,
            String unCC, String unAsunto, String unMensaje, UsuariosVO usuarioReactivovigilancia, String Operacion, int intento) {
        String log = "";
        log += "****************************************\n";
        log += "****************************************\n";
        log += "****************************************\n";
        log += "****************************************  Enviando correo\n";
        log += "Intento:" + intento + "\n";
        log += "Fecha:" + new Date() + "\n";
        log += "De: " + unRemitente + "\n";
        log += "Para: " + unDestinatario + "\n";
        log += "CC: " + unCC + "\n";
        log += "ServidorCorreo: " + unServidorCorreo + "\n";
        log += "usuario SMTP: " + usuario + "\n";
        log += "unAsunto: " + unAsunto + "\n";
        log += "unMensaje: " + unMensaje + "\n";
        try {
            log += "Usuario Tecnovigilancia: " + usuarioReactivovigilancia.getInfo() + "\n";
        } catch (Exception e) {
            log += "Usuario Reactivovigilancia: INVALIDO" + "\n";
        }
        log += "Operacion: " + Operacion + "\n";

        logCorreoEnviadosReactivovigilancia.info(log);
    }
}
